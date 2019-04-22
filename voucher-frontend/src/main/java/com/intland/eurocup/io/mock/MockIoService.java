package com.intland.eurocup.io.mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.intland.eurocup.common.model.LotResult;
import com.intland.eurocup.controller.response.ResponseStorage;
import com.intland.eurocup.io.SenderService;
import com.intland.eurocup.model.Response;
import com.intland.eurocup.model.ResponseStatus;
import com.intland.eurocup.model.Voucher;

/**
 * Mocked IO Service. It is used to test application without JMS and Back end
 * application. It randomize responses and simulates long running answer.
 */
@Component
@Profile("dev")
public class MockIoService implements SenderService {
  private static final List<LotResult> VALUES = Collections.unmodifiableList(Arrays.asList(LotResult.values()));
  private static final int SIZE = VALUES.size();
  private static final Random RANDOM = new Random();
  private static final Long TIMEOUT = 5000L;

  @Autowired
  private ResponseStorage responseStorage;

  @Override
  public void send(final Voucher voucher) {
    runWorkerThread(voucher.getId());
  }

  private void runWorkerThread(Long id) {
    Runnable runnable = () -> {
      try {
        Thread.sleep(TIMEOUT);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      responseStorage.registerRequestId(id);
      responseStorage.save(id, getResponse());
    };
    Thread t = new Thread(runnable);
    t.start();
  }

  private Response getResponse() {
    Response response;
    final LotResult result = getRandomLotResult();
    if (result == LotResult.WINNER || result == LotResult.LOSER) {
      response = new Response(ResponseStatus.OK, result.getDescription());
    } else {
      response = new Response(ResponseStatus.ERROR, result.getDescription());
    }
    return response;
  }

  private LotResult getRandomLotResult() {
    return VALUES.get(RANDOM.nextInt(SIZE));
  }
}

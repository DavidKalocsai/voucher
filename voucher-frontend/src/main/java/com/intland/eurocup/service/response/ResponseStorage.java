package com.intland.eurocup.service.response;

import com.intland.eurocup.model.Response;

/**
 * Interface to store responses. 
 */
public interface ResponseStorage {
  /**
   * Register a request id, before it is sent to the back end. Only those
   * responses will be saved which request id is previously saved into map.
   * 
   * @param requestId id to register.
   */
  void register(Long requestId);

  /**
   * Save response in storage if request id is found it storage.
   * 
   * @param requestId id of the request.
   * @param response  {@link Response}.
   */
  void save(Long requestId, Response response);

  /**
   * Get {@link Response} based on Id. If response arrived, before returning
   * remove it from map.
   * 
   * @param id identifies response.
   * @return {@link Response}.
   */
  Response get(Long id);
  
  /**
   * Clean up storage. Remove left-overs.
   */
  void clean();
  
}

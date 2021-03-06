package io.github.hapjava.accessories;

import io.github.hapjava.characteristics.HomekitCharacteristicChangeCallback;
import io.github.hapjava.characteristics.impl.windowcovering.PositionStateEnum;
import io.github.hapjava.services.Service;
import io.github.hapjava.services.impl.WindowCoveringService;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

/** A window covering, like blinds, which can be remotely controlled. */
public interface WindowCoveringAccessory extends HomekitAccessory {
  /**
   * Retrieves the current position
   *
   * @return a future that will contain the position as a value between 0 and 100
   */
  CompletableFuture<Integer> getCurrentPosition();

  /**
   * Retrieves the target position
   *
   * @return a future that will contain the target position as a value between 0 and 100
   */
  CompletableFuture<Integer> getTargetPosition();

  /**
   * Retrieves the state of the position: increasing, decreasing, or stopped
   *
   * @return a future that will contain the current state
   */
  CompletableFuture<PositionStateEnum> getPositionState();

  /**
   * Sets the target position
   *
   * @param position the target position to set, as a value between 1 and 100
   * @return a future that completes when the change is made
   * @throws Exception when the change cannot be made
   */
  CompletableFuture<Void> setTargetPosition(int position) throws Exception;

  /**
   * Subscribes to changes in the current position.
   *
   * @param callback the function to call when the state changes.
   */
  void subscribeCurrentPosition(HomekitCharacteristicChangeCallback callback);

  /**
   * Subscribes to changes in the target position.
   *
   * @param callback the function to call when the state changes.
   */
  void subscribeTargetPosition(HomekitCharacteristicChangeCallback callback);

  /**
   * Subscribes to changes in the position state: increasing, decreasing, or stopped
   *
   * @param callback the function to call when the state changes.
   */
  void subscribePositionState(HomekitCharacteristicChangeCallback callback);

  /** Unsubscribes from changes in the current position. */
  void unsubscribeCurrentPosition();

  /** Unsubscribes from changes in the target position. */
  void unsubscribeTargetPosition();

  /** Unsubscribes from changes in the position state */
  void unsubscribePositionState();

  @Override
  default Collection<Service> getServices() {
    return Collections.singleton(new WindowCoveringService(this));
  }
}

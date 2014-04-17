package com.redhat.synq;

import java.util.function.Consumer;

// The idea here would be to have MultiEvent implement Event<Fork<T,U>> where t and u are the types
// of the events contained in the "multievent". When waited for, it would return the fork, which
// tests could then say what they would like to do given what outcome.
// Not sure if this is a good pattern yet... but the possibility of reducing methods on page objects
// is enticing (instead of having loginExpectingSucces and loginExpectingFailure, you can just have
// Fork<LoginPage, DesiredDestination> login(...) where you can let the test handle what to do after
// that. Is this a good idea?
public interface Fork<T, U> {
    void onT(Consumer<T> callback);
    void onU(Consumer<U> callback);
}

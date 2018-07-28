/*
 * Copyright (c) 2018. Uber Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uber.autodispose;

import io.reactivex.Maybe;
import io.reactivex.subjects.MaybeSubject;

final class TestUtil {

  private static final ScopeProvider OUTSIDE_SCOPE_PROVIDER = new ScopeProvider() {
    @Override public Maybe<?> requestScope() {
      throw new OutsideScopeException("Outside scope!");
    }
  };

  private TestUtil() {
    throw new InstantiationError();
  }

  static ScopeProvider makeProvider(final MaybeSubject<Integer> scope) {
    return new ScopeProvider() {
      @Override public Maybe<?> requestScope() {
        return scope;
      }
    };
  }

  static ScopeProvider outsideScopeProvider() {
    return OUTSIDE_SCOPE_PROVIDER;
  }
}

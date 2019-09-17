// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.firebase.installations.local;

import static com.google.common.truth.Truth.assertAbout;

import com.google.common.truth.FailureMetadata;
import com.google.common.truth.Subject;
import com.google.firebase.installations.local.PersistedFid.RegistrationStatus;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class PersistedFidEntrySubject extends Subject {

  // User-defined entry point
  public static PersistedFidEntrySubject assertThat(
      @NullableDecl PersistedFidEntry persistedFidEntry) {
    return assertAbout(PERSISTED_FID_ENTRY_SUBJECT_FACTORY).that(persistedFidEntry);
  }

  // Static method for getting the subject factory (for use with assertAbout())
  public static Subject.Factory<PersistedFidEntrySubject, PersistedFidEntry> persistedFidEntry() {
    return PERSISTED_FID_ENTRY_SUBJECT_FACTORY;
  }

  // Boiler-plate Subject.Factory for EmployeeSubject
  private static final Subject.Factory<PersistedFidEntrySubject, PersistedFidEntry>
      PERSISTED_FID_ENTRY_SUBJECT_FACTORY = PersistedFidEntrySubject::new;

  private final PersistedFidEntry actual;

  /**
   * Constructor for use by subclasses. If you want to create an instance of this class itself, call
   * {@link Subject#check(String, PersistedFidEntry ..) check(...)}{@code .that(actual)}.
   *
   * @param metadata
   * @param actual
   */
  protected PersistedFidEntrySubject(
      FailureMetadata metadata, @NullableDecl PersistedFidEntry actual) {
    super(metadata, actual);
    this.actual = actual;
  }

  // User-defined test assertion

  public void hasFid(String fid) {
    check("getFirebaseInstallationId()").that(actual.getFirebaseInstallationId()).isEqualTo(fid);
  }

  public void hasAuthToken(String authToken) {
    check("getAuthToken()").that(actual.getAuthToken()).isEqualTo(authToken);
  }

  public void hasRefreshToken(String refreshToken) {
    check("getRefreshToken()").that(actual.getRefreshToken()).isEqualTo(refreshToken);
  }

  public void hasCreationTimestamp(long creationTimestamp) {
    check("getTokenCreationEpochInSecs()")
        .that(actual.getTokenCreationEpochInSecs())
        .isEqualTo(creationTimestamp);
  }

  public void hasTokenExpirationTimestamp(long tokenExpirationTimestamp) {
    check("getExpiresInSecs()").that(actual.getExpiresInSecs()).isEqualTo(tokenExpirationTimestamp);
  }

  public void hasRegistrationStatus(RegistrationStatus registrationStatus) {
    check("getRegistrationStatus()")
        .that(actual.getRegistrationStatus())
        .isEqualTo(registrationStatus);
  }
}
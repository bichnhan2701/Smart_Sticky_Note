package com.example.smartstickynote.di;

import com.example.smartstickynote.domain.repository.AuthRepository;
import com.google.firebase.auth.FirebaseAuth;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class AuthModule_ProvideAuthRepositoryFactory implements Factory<AuthRepository> {
  private final Provider<FirebaseAuth> authProvider;

  public AuthModule_ProvideAuthRepositoryFactory(Provider<FirebaseAuth> authProvider) {
    this.authProvider = authProvider;
  }

  @Override
  public AuthRepository get() {
    return provideAuthRepository(authProvider.get());
  }

  public static AuthModule_ProvideAuthRepositoryFactory create(
      Provider<FirebaseAuth> authProvider) {
    return new AuthModule_ProvideAuthRepositoryFactory(authProvider);
  }

  public static AuthRepository provideAuthRepository(FirebaseAuth auth) {
    return Preconditions.checkNotNullFromProvides(AuthModule.INSTANCE.provideAuthRepository(auth));
  }
}

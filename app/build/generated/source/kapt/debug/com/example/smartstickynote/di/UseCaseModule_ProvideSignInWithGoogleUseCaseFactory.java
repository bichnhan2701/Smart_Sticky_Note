package com.example.smartstickynote.di;

import com.example.smartstickynote.domain.repository.AuthRepository;
import com.example.smartstickynote.domain.usecase.SignInWithGoogleUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class UseCaseModule_ProvideSignInWithGoogleUseCaseFactory implements Factory<SignInWithGoogleUseCase> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public UseCaseModule_ProvideSignInWithGoogleUseCaseFactory(
      Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public SignInWithGoogleUseCase get() {
    return provideSignInWithGoogleUseCase(authRepositoryProvider.get());
  }

  public static UseCaseModule_ProvideSignInWithGoogleUseCaseFactory create(
      Provider<AuthRepository> authRepositoryProvider) {
    return new UseCaseModule_ProvideSignInWithGoogleUseCaseFactory(authRepositoryProvider);
  }

  public static SignInWithGoogleUseCase provideSignInWithGoogleUseCase(
      AuthRepository authRepository) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideSignInWithGoogleUseCase(authRepository));
  }
}

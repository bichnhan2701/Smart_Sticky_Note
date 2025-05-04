package com.example.smartstickynote.ui.viewmodel;

import com.example.smartstickynote.domain.repository.AuthRepository;
import com.example.smartstickynote.domain.usecase.SignInWithGoogleUseCase;
import com.example.smartstickynote.domain.usecase.SignOutUseCase;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class UserProfileViewModel_Factory implements Factory<UserProfileViewModel> {
  private final Provider<SignInWithGoogleUseCase> signInWithGoogleUseCaseProvider;

  private final Provider<SignOutUseCase> signOutUseCaseProvider;

  private final Provider<GoogleSignInClient> googleSignInClientProvider;

  private final Provider<AuthRepository> authRepositoryProvider;

  public UserProfileViewModel_Factory(
      Provider<SignInWithGoogleUseCase> signInWithGoogleUseCaseProvider,
      Provider<SignOutUseCase> signOutUseCaseProvider,
      Provider<GoogleSignInClient> googleSignInClientProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    this.signInWithGoogleUseCaseProvider = signInWithGoogleUseCaseProvider;
    this.signOutUseCaseProvider = signOutUseCaseProvider;
    this.googleSignInClientProvider = googleSignInClientProvider;
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public UserProfileViewModel get() {
    return newInstance(signInWithGoogleUseCaseProvider.get(), signOutUseCaseProvider.get(), googleSignInClientProvider.get(), authRepositoryProvider.get());
  }

  public static UserProfileViewModel_Factory create(
      Provider<SignInWithGoogleUseCase> signInWithGoogleUseCaseProvider,
      Provider<SignOutUseCase> signOutUseCaseProvider,
      Provider<GoogleSignInClient> googleSignInClientProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    return new UserProfileViewModel_Factory(signInWithGoogleUseCaseProvider, signOutUseCaseProvider, googleSignInClientProvider, authRepositoryProvider);
  }

  public static UserProfileViewModel newInstance(SignInWithGoogleUseCase signInWithGoogleUseCase,
      SignOutUseCase signOutUseCase, GoogleSignInClient googleSignInClient,
      AuthRepository authRepository) {
    return new UserProfileViewModel(signInWithGoogleUseCase, signOutUseCase, googleSignInClient, authRepository);
  }
}

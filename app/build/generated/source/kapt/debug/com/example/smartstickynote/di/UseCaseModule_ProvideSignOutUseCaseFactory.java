package com.example.smartstickynote.di;

import com.example.smartstickynote.domain.repository.AuthRepository;
import com.example.smartstickynote.domain.usecase.SignOutUseCase;
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
public final class UseCaseModule_ProvideSignOutUseCaseFactory implements Factory<SignOutUseCase> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public UseCaseModule_ProvideSignOutUseCaseFactory(
      Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public SignOutUseCase get() {
    return provideSignOutUseCase(authRepositoryProvider.get());
  }

  public static UseCaseModule_ProvideSignOutUseCaseFactory create(
      Provider<AuthRepository> authRepositoryProvider) {
    return new UseCaseModule_ProvideSignOutUseCaseFactory(authRepositoryProvider);
  }

  public static SignOutUseCase provideSignOutUseCase(AuthRepository authRepository) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideSignOutUseCase(authRepository));
  }
}

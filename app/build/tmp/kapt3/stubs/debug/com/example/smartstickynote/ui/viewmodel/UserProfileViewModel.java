package com.example.smartstickynote.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ0\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020!0%2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020!0\'J\u0006\u0010)\u001a\u00020!J\u0006\u0010*\u001a\u00020!R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R+\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R+\u0010\u0013\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R+\u0010\u0016\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0016\u0010\u000e\"\u0004\b\u0017\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R/\u0010\u001a\u001a\u0004\u0018\u00010\u00192\b\u0010\u000b\u001a\u0004\u0018\u00010\u00198F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001f\u0010\u0012\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e\u00a8\u0006+"}, d2 = {"Lcom/example/smartstickynote/ui/viewmodel/UserProfileViewModel;", "Landroidx/lifecycle/ViewModel;", "signInWithGoogleUseCase", "Lcom/example/smartstickynote/domain/usecase/SignInWithGoogleUseCase;", "signOutUseCase", "Lcom/example/smartstickynote/domain/usecase/SignOutUseCase;", "googleSignInClient", "Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;", "authRepository", "Lcom/example/smartstickynote/domain/repository/AuthRepository;", "(Lcom/example/smartstickynote/domain/usecase/SignInWithGoogleUseCase;Lcom/example/smartstickynote/domain/usecase/SignOutUseCase;Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;Lcom/example/smartstickynote/domain/repository/AuthRepository;)V", "<set-?>", "", "isDarkMode", "()Z", "setDarkMode", "(Z)V", "isDarkMode$delegate", "Landroidx/compose/runtime/MutableState;", "isLoading", "setLoading", "isLoading$delegate", "isUserLoggedIn", "setUserLoggedIn", "isUserLoggedIn$delegate", "Lcom/google/firebase/auth/FirebaseUser;", "user", "getUser", "()Lcom/google/firebase/auth/FirebaseUser;", "setUser", "(Lcom/google/firebase/auth/FirebaseUser;)V", "user$delegate", "signInWithGoogleIntent", "", "intent", "Landroid/content/Intent;", "onSuccess", "Lkotlin/Function0;", "onError", "Lkotlin/Function1;", "", "signOut", "toggleDarkMode", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class UserProfileViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartstickynote.domain.usecase.SignInWithGoogleUseCase signInWithGoogleUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartstickynote.domain.usecase.SignOutUseCase signOutUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.android.gms.auth.api.signin.GoogleSignInClient googleSignInClient = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState isDarkMode$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState isUserLoggedIn$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState user$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState isLoading$delegate = null;
    
    @javax.inject.Inject()
    public UserProfileViewModel(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.usecase.SignInWithGoogleUseCase signInWithGoogleUseCase, @org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.usecase.SignOutUseCase signOutUseCase, @org.jetbrains.annotations.NotNull()
    com.google.android.gms.auth.api.signin.GoogleSignInClient googleSignInClient, @org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.repository.AuthRepository authRepository) {
        super();
    }
    
    public final boolean isDarkMode() {
        return false;
    }
    
    private final void setDarkMode(boolean p0) {
    }
    
    public final boolean isUserLoggedIn() {
        return false;
    }
    
    private final void setUserLoggedIn(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.auth.FirebaseUser getUser() {
        return null;
    }
    
    private final void setUser(com.google.firebase.auth.FirebaseUser p0) {
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    private final void setLoading(boolean p0) {
    }
    
    public final void signInWithGoogleIntent(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onError) {
    }
    
    public final void signOut() {
    }
    
    public final void toggleDarkMode() {
    }
}
package com.example.smartstickynote.domain.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J$\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\b\u0010\tJ\b\u0010\n\u001a\u00020\u000bH&\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\f"}, d2 = {"Lcom/example/smartstickynote/domain/repository/AuthRepository;", "", "getCurrentUser", "Lcom/google/firebase/auth/FirebaseUser;", "signInWithGoogle", "Lkotlin/Result;", "idToken", "", "signInWithGoogle-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signOut", "", "app_debug"})
public abstract interface AuthRepository {
    
    public abstract void signOut();
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.google.firebase.auth.FirebaseUser getCurrentUser();
}
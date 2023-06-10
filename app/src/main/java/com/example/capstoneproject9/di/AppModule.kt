package com.example.capstoneproject9.di

import android.app.Application
import android.content.Context
import androidx.paging.PagingConfig
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.capstoneproject9.R
import com.example.capstoneproject9.core.AppConstants.SIGN_IN_REQUEST
import com.example.capstoneproject9.core.AppConstants.SIGN_UP_REQUEST
import com.example.capstoneproject9.core.FirebaseConstants.PAGE_SIZE
import com.example.capstoneproject9.data.repository.*
import com.example.capstoneproject9.domain.repository.*
import com.example.capstoneproject9.repository.TicketingRepositoryImpl
import com.example.capstoneproject9.repository.UploadImageRepositoryImpl
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideFirebaseAuth() = Firebase.auth                    //Firebase Authentication Instance

    @Provides
    fun provideFirebaseDatabase() = Firebase.database           //Firebase Database Instance

    @Provides
    fun provideFirebaseStorage() = Firebase.storage

    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore         //Firebase Firestore Instance

    @Provides
    fun provideOneTapClient(
        @ApplicationContext
        context: Context
    ) = Identity.getSignInClient(context)

    @Provides
    @Named(SIGN_IN_REQUEST)
    fun provideSignInRequest(
        app: Application
    ) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(app.getString(R.string.web_client_id))
                .setFilterByAuthorizedAccounts(false)         // default true
                .build())
        .setAutoSelectEnabled(false)                           // defaul true
        .build()

    @Provides
    @Named(SIGN_UP_REQUEST)
    fun provideSignUpRequest(
        app: Application
    ) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(app.getString(R.string.web_client_id))
                .setFilterByAuthorizedAccounts(false)
                .build())
        .build()

    @Provides
    fun provideGoogleSignInOptions(
        app: Application
    ) = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(app.getString(R.string.web_client_id))
        .requestEmail()
        .build()

    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,                                             // Authentication Instance
        oneTapClient: SignInClient,
        @Named(SIGN_IN_REQUEST)
        signInRequest: BeginSignInRequest,
        @Named(SIGN_UP_REQUEST)
        signUpRequest: BeginSignInRequest,
        db: FirebaseFirestore                                           // Firestore Instance
    ): AuthRepository = AuthRepositoryImpl(
        auth = auth,
        oneTapClient = oneTapClient,
        signInRequest = signInRequest,
        signUpRequest = signUpRequest,
        db = db
    )

    @Provides
    fun provideMainRepository(
        auth: FirebaseAuth,                                             //Authentication Instance
        oneTapClient: SignInClient,
        firebaseDatabase: FirebaseDatabase,                             //Database Instance
        firebaseFirestore: FirebaseFirestore,                           //Firestore Instance
        config: PagingConfig
    ): MainRepository = MainRepositoryImpl(
        auth = auth,
        oneTapClient = oneTapClient,
        firebaseDatabase = firebaseDatabase,
        firebaseFirestore = firebaseFirestore,
        config = config
    )

    @Provides
    fun provideBrandProductsRepository(
        firebaseFirestore: FirebaseFirestore,                           //Firestore Instance
        config: PagingConfig
    ): BrandProductsRepository = BrandProductsRepositoryImpl(
        db = firebaseFirestore,
        config = config
    )

    @Provides
    fun provideProductDetailsRepository(
        firebaseDatabase: FirebaseDatabase,                              //Database Instance
        firebaseFirestore: FirebaseFirestore,                            // Firestore Instance
        auth: FirebaseAuth
    ): ProductDetailsRepository = ProductDetailsRepositoryImpl(
        firebaseDatabase = firebaseDatabase,
        firebaseFirestore = firebaseFirestore,
        auth = auth
    )

    @Provides
    fun provideProductSearchRepository(
        firebaseFirestore: FirebaseFirestore,                          //Firestore Instance
        config: PagingConfig
    ): ProductSearchRepository = ProductSearchRepositoryImpl(
        db = firebaseFirestore,
        config = config
    )

    @Provides
    fun provideShoppingCartRepository(
        firebaseDatabase: FirebaseDatabase,                             //Database Instance
        firebaseFirestore: FirebaseFirestore,                           //Firestore Instance
        auth: FirebaseAuth                                              //Authentication Instance
    ): ShoppingCartRepository = ShoppingCartRepositoryImpl(
        firebaseDatabase = firebaseDatabase,
        firebaseFirestore = firebaseFirestore,
        auth = auth
    )
    @Provides
    fun provideOrderDetailsRepository(
        firebaseFirestore: FirebaseFirestore,                            //Firestore Instance
        auth: FirebaseAuth                                               //Authentication Instance
    ): ProductsOrderRepository = ProductsOrderRepositoryImpl(
        db = firebaseFirestore,
        auth = auth
    )

    @Provides                                                           // Will provide Ticketing Repository
    fun provideTicketingRepository(
        firebaseFirestore: FirebaseFirestore,                           //Firestore Instance
        auth: FirebaseAuth                                              //Authentication Instance
    ): TicketingRepository = TicketingRepositoryImpl(
        firebaseFirestore = firebaseFirestore,
        auth = auth
    )

    @Provides
    fun provideUploadImageRepository(
        firebaseFirestore: FirebaseFirestore,
        firebaseStorage: FirebaseStorage,
        auth: FirebaseAuth
    ):UploadImageRepository = UploadImageRepositoryImpl(
        storage =  firebaseStorage,                                       //Firebase Storage Instance
        db = firebaseFirestore,                                           //Firebase Firestore Instance
        auth = auth                                                       //Firebase Authentication Instance
    )

    @Provides
    fun providePagingConfig() = PagingConfig(
        pageSize = PAGE_SIZE.toInt()
    )
}
package com.example.capstoneproject9.core

object FirebaseConstants {
    //Realtime Database Nodes and Firestore Collections
    const val BANNERS = "banners"
    const val BRANDS = "brands"                                               //const val BRANDS = "brands"
    const val SHOPPING_CARTS = "shoppingCarts"


    //ALL PRIMARY COLLECTION
    const val USERS = "users"
    const val ALL_PRODUCT_ORDER = "allProductOrder"
    const val FAQ = "faq"
    const val PRODUCTS = "products"



    //collection inside USERS
    const val ORDERS = "orders"
    const val PRODUCTS_ORDER = "productsOrder"
    const val SHOPPING_CART = "shoppingCart"
    const val SUBMITTED_TICKET = "submittedTicket"


    //Collection and Document inside productsOrder
    const val TRACKING_DETAILS = "trackingDetails"
    const val PAYMENT_DETAILS  = "paymentDetails"


    const val TRACKING_STATUS = "trackingStatus"


    //Firebase Fields
    const val CREATED_AT = "createdAt"
    const val DISPLAY_NAME = "displayName"
    const val EMAIL = "email"
    const val BRAND = "brand"                                                  //const val BRAND = "brand"
    const val PHOTO_URL = "photoUrl"
    const val CREATION_DATE = "creationDate"
    const val FAVORITES = "favorites"
    const val NAME = "name"
    const val POPULAR = "popular"
    const val QUANTITY = "quantity"
    const val ADDITION_DATE = "additionDate"
    const val ITEMS = "items"
    const val ID = "id"
    const val DATE_OF_SUBMISSION = "dateOfSubmission"
    const val TOTAL = "total"


    //PAYMONGO REFERENCE
    const val ADDRESS = "address"
    const val REFERENCE = "referenceNumber"
    const val CHECK_OUT_URL = "checkOutUrl"
    const val PAYMENT_STATUS = "paymentStatus"


    //TICKETING REFERENCE
    const val SUBJECT = "subject"
    const val PROBLEM = "problem"
    const val NUMBER = "number"

    //Cloud Storage Folders
    const val THUMBS = "thumbs"
    const val IMAGES = "images"
    const val CUSTOMIZE_ORDER = "customizeOrder"

    //CUSTOMIZE ORDER REFERENCE
    const val ALL_IMAGES = "image/*"
    const val OPEN_GALLERY = "Open Gallery"
    const val IMAGE_SUCCESSFULLY_ADDED_MESSAGE = "Image successfully added."
    const val DISPLAY_IT_MESSAGE = "Display it?"

    //Paging Limit
    const val PAGE_SIZE = 8L

    //Base URLs
    const val STORAGE_BASE_URL = "https://firebasestorage.googleapis.com/v0/b"
}
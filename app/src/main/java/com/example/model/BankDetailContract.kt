package com.example.model

interface BankDetailContract {
     open fun  getAsync(clazz: Class<BankDetails>, key: String? ,defaultFileName:String?): io.reactivex.Single<BankDetails>
}
package data

class Data_Or_Exception<T, Boolean, E: Exception> (
    var data: T? = null,
    var loading: Boolean? = null,
    var e: E? = null
)

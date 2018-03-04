package com.apollo.base.data.models

import com.apollo.base.data.models.ModelWithMetadata
import java.util.*


data class User(
        override var id: String = "ERROR-" + UUID.randomUUID().toString(),
        var email: String = "ERROR",
        var name: String? = null,
        var pictureUrl: String? = null
) : ModelWithMetadata(id = id)


package com.apollo.base.data.models

import com.apollo.base.extensions.timestampNow
import java.util.*

open class ModelWithMetadata(
        open var id       : String = UUID.randomUUID().toString(),
        open var createdAt: Long   = timestampNow(),
        open var updatedAt: Long   = timestampNow()
)

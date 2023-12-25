package com.shakelang.util.jvmlib.infos.constants

interface ConstantUser {
    val uses: Array<ConstantInfo>
    val users: Array<ConstantUser> get() = arrayOf(this)
}

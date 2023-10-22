package io.github.shakelang.jvmlib.infos.constants

interface ConstantUser {
    val uses: Array<ConstantInfo>
    val users: Array<ConstantUser> get() = arrayOf(this)
}
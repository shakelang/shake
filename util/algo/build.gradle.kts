import com.shakelang.util.changelog.public
import com.shakelang.util.changelog.resolveVersion
import conventions.projectGroup
import conventions.useKotest

plugins {
    id("conventions.all")
}

group = projectGroup("util")
version = resolveVersion()
description = "Implementation of default algorithms and data structures"
public = true

val projectName = name

useKotest()

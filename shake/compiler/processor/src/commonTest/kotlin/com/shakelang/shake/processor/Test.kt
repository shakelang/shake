package com.shakelang.shake.processor

import com.shakelang.shake.processor.program.map.InformationRecreator
import com.shakelang.shake.processor.program.map.MapGenerator
import com.shakelang.shake.processor.program.map.MapReader
import com.shakelang.util.io.streaming.input.bytes.dataStream
import com.shakelang.util.io.streaming.output.bytes.ByteArrayOutputStream
import com.shakelang.util.shason.json
import io.kotest.core.spec.style.FreeSpec

class Test :
    FreeSpec(
        {
            "test" {
                val processor = createBaseProcessor()
                val byteStream = ByteArrayOutputStream()
                val mapGenerator = MapGenerator(byteStream)
                mapGenerator.store(processor.finish())
                val bytes = byteStream.toByteArray()
                println(bytes.size)

                val mapReader = MapReader(bytes.dataStream())
                val project = mapReader.readProject()
                println(json.stringify(project.json()))

                // recreate the CreationShakeProject

                val recreator = InformationRecreator()
                val recreated = recreator.recreate(project)
            }
        },
    )

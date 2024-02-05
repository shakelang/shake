package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.PCast
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class PCastTests : FreeSpec(
    {
        "PCast.invoke(UByte) shortcut" {
            for (i in 0..255) {
                val from = ((i and 0xF0) ushr 4).toUByte()
                val to = (i and 0x0F).toUByte()
                val cast = PCast(from, to)
                cast shouldBe i.toUByte()
            }
        }

        "PCast.invoke(Byte) shortcut" {
            for (i in 0..255) {
                val from = ((i and 0xF0) ushr 4).toByte()
                val to = (i and 0x0F).toByte()
                val cast = PCast(from, to)
                cast shouldBe i.toUByte()
            }
        }

        "PCast.create(UByte) shortcut" {
            for (i in 0..255) {
                val from = ((i and 0xF0) ushr 4).toUByte()
                val to = (i and 0x0F).toUByte()
                val cast = PCast.create(from, to)
                cast shouldBe i.toUByte()
            }
        }

        "PCast.create(Byte) shortcut" {
            for (i in 0..255) {
                val from = ((i and 0xF0) ushr 4).toByte()
                val to = (i and 0x0F).toByte()
                val cast = PCast.create(from, to)
                cast shouldBe i.toUByte()
            }
        }

        "PCast.BYTE should be 0x0" { PCast.BYTE shouldBe 0x00.toUByte() }
        "PCast.SHORT should be 0x1" { PCast.SHORT shouldBe 0x01.toUByte() }
        "PCast.INT should be 0x2" { PCast.INT shouldBe 0x02.toUByte() }
        "PCast.LONG should be 0x3" { PCast.LONG shouldBe 0x03.toUByte() }
        "PCast.UBYTE should be 0x4" { PCast.UBYTE shouldBe 0x04.toUByte() }
        "PCast.USHORT should be 0x5" { PCast.USHORT shouldBe 0x05.toUByte() }
        "PCast.UINT should be 0x6" { PCast.UINT shouldBe 0x06.toUByte() }
        "PCast.ULONG should be 0x7" { PCast.ULONG shouldBe 0x07.toUByte() }
        "PCast.FLOAT should be 0x8" { PCast.FLOAT shouldBe 0x08.toUByte() }
        "PCast.DOUBLE should be 0x9" { PCast.DOUBLE shouldBe 0x09.toUByte() }

        "PCast.BYTE_TO_BYTE should be 0x00" { PCast.BYTE_TO_BYTE shouldBe 0x00.toUByte() }
        "PCast.BYTE_TO_SHORT should be 0x01" { PCast.BYTE_TO_SHORT shouldBe 0x01.toUByte() }
        "PCast.BYTE_TO_INT should be 0x02" { PCast.BYTE_TO_INT shouldBe 0x02.toUByte() }
        "PCast.BYTE_TO_LONG should be 0x03" { PCast.BYTE_TO_LONG shouldBe 0x03.toUByte() }
        "PCast.BYTE_TO_UBYTE should be 0x04" { PCast.BYTE_TO_UBYTE shouldBe 0x04.toUByte() }
        "PCast.BYTE_TO_USHORT should be 0x05" { PCast.BYTE_TO_USHORT shouldBe 0x05.toUByte() }
        "PCast.BYTE_TO_UINT should be 0x06" { PCast.BYTE_TO_UINT shouldBe 0x06.toUByte() }
        "PCast.BYTE_TO_ULONG should be 0x07" { PCast.BYTE_TO_ULONG shouldBe 0x07.toUByte() }
        "PCast.BYTE_TO_FLOAT should be 0x08" { PCast.BYTE_TO_FLOAT shouldBe 0x08.toUByte() }
        "PCast.BYTE_TO_DOUBLE should be 0x09" { PCast.BYTE_TO_DOUBLE shouldBe 0x09.toUByte() }

        "PCast.SHORT_TO_BYTE should be 0x10" { PCast.SHORT_TO_BYTE shouldBe 0x10.toUByte() }
        "PCast.SHORT_TO_SHORT should be 0x11" { PCast.SHORT_TO_SHORT shouldBe 0x11.toUByte() }
        "PCast.SHORT_TO_INT should be 0x12" { PCast.SHORT_TO_INT shouldBe 0x12.toUByte() }
        "PCast.SHORT_TO_LONG should be 0x13" { PCast.SHORT_TO_LONG shouldBe 0x13.toUByte() }
        "PCast.SHORT_TO_UBYTE should be 0x14" { PCast.SHORT_TO_UBYTE shouldBe 0x14.toUByte() }
        "PCast.SHORT_TO_USHORT should be 0x15" { PCast.SHORT_TO_USHORT shouldBe 0x15.toUByte() }
        "PCast.SHORT_TO_UINT should be 0x16" { PCast.SHORT_TO_UINT shouldBe 0x16.toUByte() }
        "PCast.SHORT_TO_ULONG should be 0x17" { PCast.SHORT_TO_ULONG shouldBe 0x17.toUByte() }
        "PCast.SHORT_TO_FLOAT should be 0x18" { PCast.SHORT_TO_FLOAT shouldBe 0x18.toUByte() }
        "PCast.SHORT_TO_DOUBLE should be 0x19" { PCast.SHORT_TO_DOUBLE shouldBe 0x19.toUByte() }

        "PCast.INT_TO_BYTE should be 0x20" { PCast.INT_TO_BYTE shouldBe 0x20.toUByte() }
        "PCast.INT_TO_SHORT should be 0x21" { PCast.INT_TO_SHORT shouldBe 0x21.toUByte() }
        "PCast.INT_TO_INT should be 0x22" { PCast.INT_TO_INT shouldBe 0x22.toUByte() }
        "PCast.INT_TO_LONG should be 0x23" { PCast.INT_TO_LONG shouldBe 0x23.toUByte() }
        "PCast.INT_TO_UBYTE should be 0x24" { PCast.INT_TO_UBYTE shouldBe 0x24.toUByte() }
        "PCast.INT_TO_USHORT should be 0x25" { PCast.INT_TO_USHORT shouldBe 0x25.toUByte() }
        "PCast.INT_TO_UINT should be 0x26" { PCast.INT_TO_UINT shouldBe 0x26.toUByte() }
        "PCast.INT_TO_ULONG should be 0x27" { PCast.INT_TO_ULONG shouldBe 0x27.toUByte() }
        "PCast.INT_TO_FLOAT should be 0x28" { PCast.INT_TO_FLOAT shouldBe 0x28.toUByte() }
        "PCast.INT_TO_DOUBLE should be 0x29" { PCast.INT_TO_DOUBLE shouldBe 0x29.toUByte() }

        "PCast.LONG_TO_BYTE should be 0x30" { PCast.LONG_TO_BYTE shouldBe 0x30.toUByte() }
        "PCast.LONG_TO_SHORT should be 0x31" { PCast.LONG_TO_SHORT shouldBe 0x31.toUByte() }
        "PCast.LONG_TO_INT should be 0x32" { PCast.LONG_TO_INT shouldBe 0x32.toUByte() }
        "PCast.LONG_TO_LONG should be 0x33" { PCast.LONG_TO_LONG shouldBe 0x33.toUByte() }
        "PCast.LONG_TO_UBYTE should be 0x34" { PCast.LONG_TO_UBYTE shouldBe 0x34.toUByte() }
        "PCast.LONG_TO_USHORT should be 0x35" { PCast.LONG_TO_USHORT shouldBe 0x35.toUByte() }
        "PCast.LONG_TO_UINT should be 0x36" { PCast.LONG_TO_UINT shouldBe 0x36.toUByte() }
        "PCast.LONG_TO_ULONG should be 0x37" { PCast.LONG_TO_ULONG shouldBe 0x37.toUByte() }
        "PCast.LONG_TO_FLOAT should be 0x38" { PCast.LONG_TO_FLOAT shouldBe 0x38.toUByte() }
        "PCast.LONG_TO_DOUBLE should be 0x39" { PCast.LONG_TO_DOUBLE shouldBe 0x39.toUByte() }

        "PCast.UBYTE_TO_BYTE should be 0x40" { PCast.UBYTE_TO_BYTE shouldBe 0x40.toUByte() }
        "PCast.UBYTE_TO_SHORT should be 0x41" { PCast.UBYTE_TO_SHORT shouldBe 0x41.toUByte() }
        "PCast.UBYTE_TO_INT should be 0x42" { PCast.UBYTE_TO_INT shouldBe 0x42.toUByte() }
        "PCast.UBYTE_TO_LONG should be 0x43" { PCast.UBYTE_TO_LONG shouldBe 0x43.toUByte() }
        "PCast.UBYTE_TO_UBYTE should be 0x44" { PCast.UBYTE_TO_UBYTE shouldBe 0x44.toUByte() }
        "PCast.UBYTE_TO_USHORT should be 0x45" { PCast.UBYTE_TO_USHORT shouldBe 0x45.toUByte() }
        "PCast.UBYTE_TO_UINT should be 0x46" { PCast.UBYTE_TO_UINT shouldBe 0x46.toUByte() }
        "PCast.UBYTE_TO_ULONG should be 0x47" { PCast.UBYTE_TO_ULONG shouldBe 0x47.toUByte() }
        "PCast.UBYTE_TO_FLOAT should be 0x48" { PCast.UBYTE_TO_FLOAT shouldBe 0x48.toUByte() }
        "PCast.UBYTE_TO_DOUBLE should be 0x49" { PCast.UBYTE_TO_DOUBLE shouldBe 0x49.toUByte() }

        "PCast.USHORT_TO_BYTE should be 0x50" { PCast.USHORT_TO_BYTE shouldBe 0x50.toUByte() }
        "PCast.USHORT_TO_SHORT should be 0x51" { PCast.USHORT_TO_SHORT shouldBe 0x51.toUByte() }
        "PCast.USHORT_TO_INT should be 0x52" { PCast.USHORT_TO_INT shouldBe 0x52.toUByte() }
        "PCast.USHORT_TO_LONG should be 0x53" { PCast.USHORT_TO_LONG shouldBe 0x53.toUByte() }
        "PCast.USHORT_TO_UBYTE should be 0x54" { PCast.USHORT_TO_UBYTE shouldBe 0x54.toUByte() }
        "PCast.USHORT_TO_USHORT should be 0x55" { PCast.USHORT_TO_USHORT shouldBe 0x55.toUByte() }
        "PCast.USHORT_TO_UINT should be 0x56" { PCast.USHORT_TO_UINT shouldBe 0x56.toUByte() }
        "PCast.USHORT_TO_ULONG should be 0x57" { PCast.USHORT_TO_ULONG shouldBe 0x57.toUByte() }
        "PCast.USHORT_TO_FLOAT should be 0x58" { PCast.USHORT_TO_FLOAT shouldBe 0x58.toUByte() }
        "PCast.USHORT_TO_DOUBLE should be 0x59" { PCast.USHORT_TO_DOUBLE shouldBe 0x59.toUByte() }

        "PCast.UINT_TO_BYTE should be 0x60" { PCast.UINT_TO_BYTE shouldBe 0x60.toUByte() }
        "PCast.UINT_TO_SHORT should be 0x61" { PCast.UINT_TO_SHORT shouldBe 0x61.toUByte() }
        "PCast.UINT_TO_INT should be 0x62" { PCast.UINT_TO_INT shouldBe 0x62.toUByte() }
        "PCast.UINT_TO_LONG should be 0x63" { PCast.UINT_TO_LONG shouldBe 0x63.toUByte() }
        "PCast.UINT_TO_UBYTE should be 0x64" { PCast.UINT_TO_UBYTE shouldBe 0x64.toUByte() }
        "PCast.UINT_TO_USHORT should be 0x65" { PCast.UINT_TO_USHORT shouldBe 0x65.toUByte() }
        "PCast.UINT_TO_UINT should be 0x66" { PCast.UINT_TO_UINT shouldBe 0x66.toUByte() }
        "PCast.UINT_TO_ULONG should be 0x67" { PCast.UINT_TO_ULONG shouldBe 0x67.toUByte() }
        "PCast.UINT_TO_FLOAT should be 0x68" { PCast.UINT_TO_FLOAT shouldBe 0x68.toUByte() }
        "PCast.UINT_TO_DOUBLE should be 0x69" { PCast.UINT_TO_DOUBLE shouldBe 0x69.toUByte() }

        "PCast.ULONG_TO_BYTE should be 0x70" { PCast.ULONG_TO_BYTE shouldBe 0x70.toUByte() }
        "PCast.ULONG_TO_SHORT should be 0x71" { PCast.ULONG_TO_SHORT shouldBe 0x71.toUByte() }
        "PCast.ULONG_TO_INT should be 0x72" { PCast.ULONG_TO_INT shouldBe 0x72.toUByte() }
        "PCast.ULONG_TO_LONG should be 0x73" { PCast.ULONG_TO_LONG shouldBe 0x73.toUByte() }
        "PCast.ULONG_TO_UBYTE should be 0x74" { PCast.ULONG_TO_UBYTE shouldBe 0x74.toUByte() }
        "PCast.ULONG_TO_USHORT should be 0x75" { PCast.ULONG_TO_USHORT shouldBe 0x75.toUByte() }
        "PCast.ULONG_TO_UINT should be 0x76" { PCast.ULONG_TO_UINT shouldBe 0x76.toUByte() }
        "PCast.ULONG_TO_ULONG should be 0x77" { PCast.ULONG_TO_ULONG shouldBe 0x77.toUByte() }
        "PCast.ULONG_TO_FLOAT should be 0x78" { PCast.ULONG_TO_FLOAT shouldBe 0x78.toUByte() }
        "PCast.ULONG_TO_DOUBLE should be 0x79" { PCast.ULONG_TO_DOUBLE shouldBe 0x79.toUByte() }

        "PCast.FLOAT_TO_BYTE should be 0x80" { PCast.FLOAT_TO_BYTE shouldBe 0x80.toUByte() }
        "PCast.FLOAT_TO_SHORT should be 0x81" { PCast.FLOAT_TO_SHORT shouldBe 0x81.toUByte() }
        "PCast.FLOAT_TO_INT should be 0x82" { PCast.FLOAT_TO_INT shouldBe 0x82.toUByte() }
        "PCast.FLOAT_TO_LONG should be 0x83" { PCast.FLOAT_TO_LONG shouldBe 0x83.toUByte() }
        "PCast.FLOAT_TO_UBYTE should be 0x84" { PCast.FLOAT_TO_UBYTE shouldBe 0x84.toUByte() }
        "PCast.FLOAT_TO_USHORT should be 0x85" { PCast.FLOAT_TO_USHORT shouldBe 0x85.toUByte() }
        "PCast.FLOAT_TO_UINT should be 0x86" { PCast.FLOAT_TO_UINT shouldBe 0x86.toUByte() }
        "PCast.FLOAT_TO_ULONG should be 0x87" { PCast.FLOAT_TO_ULONG shouldBe 0x87.toUByte() }
        "PCast.FLOAT_TO_FLOAT should be 0x88" { PCast.FLOAT_TO_FLOAT shouldBe 0x88.toUByte() }
        "PCast.FLOAT_TO_DOUBLE should be 0x89" { PCast.FLOAT_TO_DOUBLE shouldBe 0x89.toUByte() }

        "PCast.DOUBLE_TO_BYTE should be 0x90" { PCast.DOUBLE_TO_BYTE shouldBe 0x90.toUByte() }
        "PCast.DOUBLE_TO_SHORT should be 0x91" { PCast.DOUBLE_TO_SHORT shouldBe 0x91.toUByte() }
        "PCast.DOUBLE_TO_INT should be 0x92" { PCast.DOUBLE_TO_INT shouldBe 0x92.toUByte() }
        "PCast.DOUBLE_TO_LONG should be 0x93" { PCast.DOUBLE_TO_LONG shouldBe 0x93.toUByte() }
        "PCast.DOUBLE_TO_UBYTE should be 0x94" { PCast.DOUBLE_TO_UBYTE shouldBe 0x94.toUByte() }
        "PCast.DOUBLE_TO_USHORT should be 0x95" { PCast.DOUBLE_TO_USHORT shouldBe 0x95.toUByte() }
        "PCast.DOUBLE_TO_UINT should be 0x96" { PCast.DOUBLE_TO_UINT shouldBe 0x96.toUByte() }
        "PCast.DOUBLE_TO_ULONG should be 0x97" { PCast.DOUBLE_TO_ULONG shouldBe 0x97.toUByte() }
        "PCast.DOUBLE_TO_FLOAT should be 0x98" { PCast.DOUBLE_TO_FLOAT shouldBe 0x98.toUByte() }
        "PCast.DOUBLE_TO_DOUBLE should be 0x99" { PCast.DOUBLE_TO_DOUBLE shouldBe 0x99.toUByte() }
    },
)

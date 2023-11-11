export abstract class Number {
    abstract get value(): number;

    abstract add(other: Number): Number;
    abstract add(other: number): Number;

    abstract sub(other: Number): Number;
    abstract sub(other: number): Number;

    abstract mul(other: Number): Number;
    abstract mul(other: number): Number;

    abstract div(other: Number): Number;
    abstract div(other: number): Number;

    abstract mod(other: Number): Number;
    abstract mod(other: number): Number;

    abstract neg(): Number;

    abstract shl(other: number): Number;

    abstract shr(other: number): Number;

    abstract band(other: number): Number;

    abstract bor(other: number): Number;

    abstract bxor(other: number): Number;

    abstract bnot(): Number;

    abstract binc(): Number;

    abstract bdec(): Number;

    abstract toByte(): Byte.Byte;

    abstract toShort(): Short.Short;

    abstract toInt(): Int.Int;

    abstract toLong(): Long.Long;

    abstract toFloat(): Float.Float;

    abstract toDouble(): Double.Double;
}

export module Byte {
    export function byte(value) {
        value %= 256;
        if (value >= 0) {
            if (value > 127) return value - 256;
            return value;
        }
        if (value < -128) return 256 + value;
        return value;
    }

    export function badd(a: number, b: number): number {
        return byte(a + b);
    }

    export function bsub(a: number, b: number): number {
        return byte(a - b);
    }

    export function bmul(a: number, b: number): number {
        return byte(a * b);
    }

    export function bdiv(a: number, b: number): number {
        return byte(a / b);
    }

    export function bmod(a: number, b: number): number {
        return byte(a % b);
    }

    export function bneg(a: number): number {
        return byte(-a);
    }

    export function bshl(a: number, b: number): number {
        return byte(a << b);
    }

    export function bshr(a: number, b: number): number {
        return byte(a >> b);
    }

    export function band(a: number, b: number): number {
        return byte(a & b);
    }

    export function bor(a: number, b: number): number {
        return byte(a | b);
    }

    export function bxor(a: number, b: number): number {
        return byte(a ^ b);
    }

    export function bnot(a: number): number {
        return byte(~a);
    }

    export function binc(a: number): number {
        return byte(a + 1);
    }

    export function bdec(a: number): number {
        return byte(a - 1);
    }

    export class Byte extends Number {
        readonly value: number;

        constructor(value: number) {
            super();
            this.value = byte(value);
        }

        add(b: Number);
        add(b: number);
        add(b: any): Byte {
            if (b instanceof Number) return new Byte(badd(this.value, b.value));
            return new Byte(badd(this.value, b));
        }

        sub(b: Number);
        sub(b: number);
        sub(b: any) {
            if (b instanceof Number) return new Byte(bsub(this.value, b.value));
            return new Byte(bsub(this.value, b));
        }

        mul(b: Number);
        mul(b: number);
        mul(b: any) {
            if (b instanceof Number) return new Byte(bmul(this.value, b.value));
            return new Byte(bmul(this.value, b));
        }

        div(b: Number);
        div(b: number);
        div(b: any) {
            if (b instanceof Number) return new Byte(bdiv(this.value, b.value));
            return new Byte(bdiv(this.value, b));
        }

        mod(b: Number);
        mod(b: number);
        mod(b: any) {
            if (b instanceof Number) return new Byte(bmod(this.value, b.value));
            return new Byte(bmod(this.value, b));
        }

        neg() {
            return new Byte(bneg(this.value));
        }

        shl(b: number) {
            return new Byte(bshl(this.value, b));
        }

        shr(b: number) {
            return new Byte(bshr(this.value, b));
        }

        band(b: number) {
            return new Byte(band(this.value, b));
        }

        bor(b: number) {
            return new Byte(bor(this.value, b));
        }

        bxor(b: number) {
            return new Byte(bxor(this.value, b));
        }

        bnot() {
            return new Byte(bnot(this.value));
        }

        binc() {
            return new Byte(binc(this.value));
        }

        bdec() {
            return new Byte(bdec(this.value));
        }

        toByte(): Byte {
            return this;
        }

        toShort(): Short.Short {
            return new Short.Short(this.value);
        }

        toInt(): Int.Int {
            return new Int.Int(this.value);
        }

        toLong(): Long.Long {
            return new Long.Long(this.value);
        }

        toFloat(): Float.Float {
            return new Float.Float(this.value);
        }

        toDouble(): Double.Double {
            return new Double.Double(this.value);
        }
    }
}

export module Short {
    export function short(value) {
        value %= 65536;
        if (value >= 0) {
            if (value > 32767) return value - 65536;
            return value;
        }
        if (value < -32768) return 65536 + value;
        return value;
    }

    export function sadd(a: number, b: number): number {
        return short(a + b);
    }

    export function ssub(a: number, b: number): number {
        return short(a - b);
    }

    export function smul(a: number, b: number): number {
        return short(a * b);
    }

    export function sdiv(a: number, b: number): number {
        return short(a / b);
    }

    export function smod(a: number, b: number): number {
        return short(a % b);
    }

    export function sneg(a: number): number {
        return short(-a);
    }

    export function sshl(a: number, b: number): number {
        return short(a << b);
    }

    export function sshr(a: number, b: number): number {
        return short(a >> b);
    }

    export function sand(a: number, b: number): number {
        return short(a & b);
    }

    export function sor(a: number, b: number): number {
        return short(a | b);
    }

    export function sxor(a: number, b: number): number {
        return short(a ^ b);
    }

    export function snot(a: number): number {
        return short(~a);
    }

    export function sinc(a: number): number {
        return short(a + 1);
    }

    export function sdec(a: number): number {
        return short(a - 1);
    }

    export class Short extends Number {
        readonly value: number;

        constructor(value: number) {
            super();
            this.value = short(value);
        }

        add(b: Number);
        add(b: number);
        add(b: any): Short {
            if (b instanceof Number) return new Short(sadd(this.value, b.value));
            return new Short(sadd(this.value, b));
        }

        sub(b: Number);
        sub(b: number);
        sub(b: any) {
            if (b instanceof Number) return new Short(ssub(this.value, b.value));
            return new Short(ssub(this.value, b));
        }

        mul(b: Number);
        mul(b: number);
        mul(b: any) {
            if (b instanceof Number) return new Short(smul(this.value, b.value));
            return new Short(smul(this.value, b));
        }

        div(b: Number);
        div(b: number);
        div(b: any) {
            if (b instanceof Number) return new Short(sdiv(this.value, b.value));
            return new Short(sdiv(this.value, b));
        }

        mod(b: Number);
        mod(b: number);
        mod(b: any) {
            if (b instanceof Number) return new Short(smod(this.value, b.value));
            return new Short(smod(this.value, b));
        }

        neg() {
            return new Short(sneg(this.value));
        }

        shl(b: number) {
            return new Short(sshl(this.value, b));
        }

        shr(b: number) {
            return new Short(sshr(this.value, b));
        }

        band(b: number) {
            return new Short(sand(this.value, b));
        }

        bor(b: number) {
            return new Short(sor(this.value, b));
        }

        bxor(b: number) {
            return new Short(sxor(this.value, b));
        }

        bnot() {
            return new Short(snot(this.value));
        }

        binc() {
            return new Short(sinc(this.value));
        }

        bdec() {
            return new Short(sdec(this.value));
        }

        toByte(): Byte.Byte {
            return new Byte.Byte(this.value);
        }

        toShort(): Short {
            return this;
        }

        toInt(): Int.Int {
            return new Int.Int(this.value);
        }

        toLong(): Long.Long {
            return new Long.Long(this.value);
        }

        toFloat(): Float.Float {
            return new Float.Float(this.value);
        }

        toDouble(): Double.Double {
            return new Double.Double(this.value);
        }
    }
}

export module Int {
    export function int(value) {
        value %= 4294967296;
        if (value >= 0) {
            if (value > 2147483647) return value - 4294967296;
            return value;
        }
        if (value < -2147483648) return 4294967296 + value;
        return value;
    }

    export function iadd(a: number, b: number): number {
        return int(a + b);
    }

    export function isub(a: number, b: number): number {
        return int(a - b);
    }

    export function imul(a: number, b: number): number {
        return int(a * b);
    }

    export function idiv(a: number, b: number): number {
        return int(a / b);
    }

    export function imod(a: number, b: number): number {
        return int(a % b);
    }

    export function ineg(a: number): number {
        return int(-a);
    }

    export function ishl(a: number, b: number): number {
        return int(a << b);
    }

    export function ishr(a: number, b: number): number {
        return int(a >> b);
    }

    export function iand(a: number, b: number): number {
        return int(a & b);
    }

    export function ior(a: number, b: number): number {
        return int(a | b);
    }

    export function ixor(a: number, b: number): number {
        return int(a ^ b);
    }

    export function inot(a: number): number {
        return int(~a);
    }

    export function iinc(a: number): number {
        return int(a + 1);
    }

    export function idec(a: number): number {
        return int(a - 1);
    }

    export class Int extends Number {
        readonly value: number;

        constructor(value: number) {
            super();
            this.value = int(value);
        }

        add(b: Number);
        add(b: number);
        add(b: any): Int {
            if (b instanceof Number) return new Int(iadd(this.value, b.value));
            return new Int(iadd(this.value, b));
        }

        sub(b: Number);
        sub(b: number);
        sub(b: any) {
            if (b instanceof Number) return new Int(isub(this.value, b.value));
            return new Int(isub(this.value, b));
        }

        mul(b: Number);
        mul(b: number);
        mul(b: any) {
            if (b instanceof Number) return new Int(imul(this.value, b.value));
            return new Int(imul(this.value, b));
        }

        div(b: Number);
        div(b: number);
        div(b: any) {
            if (b instanceof Number) return new Int(idiv(this.value, b.value));
            return new Int(idiv(this.value, b));
        }

        mod(b: Number);
        mod(b: number);
        mod(b: any) {
            if (b instanceof Number) return new Int(imod(this.value, b.value));
            return new Int(imod(this.value, b));
        }

        neg() {
            return new Int(ineg(this.value));
        }

        shl(b: number) {
            return new Int(ishl(this.value, b));
        }

        shr(b: number) {
            return new Int(ishr(this.value, b));
        }

        and(b: number) {
            return new Int(iand(this.value, b));
        }

        or(b: number) {
            return new Int(ior(this.value, b));
        }

        xor(b: number) {
            return new Int(ixor(this.value, b));
        }

        not() {
            return new Int(inot(this.value));
        }

        inc() {
            return new Int(iinc(this.value));
        }

        dec() {
            return new Int(idec(this.value));
        }

        toByte(): Byte.Byte {
            return new Byte.Byte(this.value);
        }

        toShort(): Short {
            return new Short(this.value);
        }

        toInt(): Int {
            return this;
        }

        toLong(): Long.Long {
            return new Long.Long(this.value);
        }

        toFloat(): Float.Float {
            return new Float.Float(this.value);
        }

        toDouble(): Double.Double {
            return new Double.Double(this.value);
        }
    }
}

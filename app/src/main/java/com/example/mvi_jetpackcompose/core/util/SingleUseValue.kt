package com.example.mvi_jetpackcompose.core.util

class SingleUseValue<T>(_value: T?) {

    private var used = false

    var value = _value
        set(value) {
            used = false
            field = value
        }

    fun peekValue(block: (event: T) -> Unit) {
        if (!used && value != null) {
            used = true
            block(value!!)
        }
    }

    override fun equals(other: Any?): Boolean {
        val otherSingleUseValue = other as? SingleUseValue<*>
        return this.value == otherSingleUseValue?.value && this.used == otherSingleUseValue?.used
    }

    override fun hashCode(): Int {
        var result = used.hashCode()
        result = 31 * result + (value?.hashCode() ?: 0)
        return result
    }


}
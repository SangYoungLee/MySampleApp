package com.syapp.componentbasicapp.structure.adapter.kinds

import com.syapp.componentbasicapp.structure.adapter.creator.HolderCreator

/**
 * Created By lsy2014 on 2019-11-05
 */
class ItemKinds {

    class GrandCodeGen {
        companion object {
            private var code = 0

            fun nextCode() = ++code
        }
    }

    interface KindCode {

        fun getCode(): Int

        fun getHolderCreator(): HolderCreator

    }
}
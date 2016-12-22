import org.junit.Test

class CollectFilterMap {

    def spmsShaping32 = [
            data          : [shaping: '32/200000.32/200000-2-7'],
            expirationTime: '2016-03-29 23:00:00 UTC',
            mnemocode     : ['budget_unlim_dpi'],
            rank          : 1,
            tags          : ['Shaping'],
    ]

    def spmsShaping64 = [
            data          : [shaping: '64/200000.64/200000-2-7'],
            expirationTime: '2016-03-29 23:00:00 UTC',
            mnemocode     : ['budget_unlim_dpi'],
            rank          : 3,
            tags          : ['Shaping_ya', 'P2P_SHAPING'],
    ]

    def spmsFH_ACC = [
            data          : [fh: [acc: 1]],
            expirationTime: '2016-03-29 23:00:00 UTC',
            mnemocode     : ['budget_unlim_dpi'],
            rank          : 1,
            tags          : ['H_ACC'],
    ]

    def spmsFH_NP_PP = [
            data          : [fh: [np: 1, pp: 79163333333]],
            expirationTime: '2016-03-29 23:00:00 UTC',
            mnemocode     : ['budget_unlim_dpi'],
            rank          : 1,
            tags          : ['H_NP_PP__TEST'],
    ]

    Map SPMS = [profile: [spmsShaping32, spmsShaping64, spmsFH_ACC, spmsFH_NP_PP]]

    static Map usingCollectWithGrep(List profile) {
        profile.collect {
            (it.data instanceof Map) ? it.data?.fh : null
        }?.grep { it != null }?.collectEntries()
    }

    static Map usingFindResults(List profile) {
        profile.findResults {
            (it.data instanceof Map) ? it.data?.fh : null
        }?.collectEntries()
    }

    static Map usingFor(List profile) {
        Map result = new LinkedHashMap()
        for (it in profile) {
            if (it.data instanceof Map && it.data?.fh)
                result.putAll(it.data?.fh)
        }
        result
    }

    static Map usingFor2(List profile) {
        Map result = new LinkedHashMap()
        for (it in profile) {
            if (it.data instanceof Map) {
                def el = it.data?.fh

                if (el)
                    result.putAll(el)
            }
        }
        result
    }

    @Test
    void benchmark() {
        def result1
        def result2
        def result3
        def result4

        benchmark {
            usingCollectWithGrep {
                result1 = usingCollectWithGrep(SPMS.profile)
            }

            usingFindResults {
                result2 = usingFindResults(SPMS.profile)
            }

            usingFor {
                result3 = usingFor(SPMS.profile)
            }

            usingFor2 {
                result4 = usingFor2(SPMS.profile)
            }
        }.prettyPrint()

        print result1

        assert result1 == result2
        assert result2 == result3
        assert result3 == result4
    }
}

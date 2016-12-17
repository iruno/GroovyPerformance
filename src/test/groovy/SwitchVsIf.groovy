
import groovy.transform.CompileStatic
import org.junit.Test

class SwitchVsIf {
    static Integer usingSwitch(Integer v) {
        Integer out

        switch (v) {
            case 4: out = 4100; break
            case 10: out = 4012; break
            case 20: out = 5031; break
            case { it in [2, 3, 15, 16] }: out = 5030; break    // Magic groovy switch here
            case { it in [11, 12, 13] }: out = 4100; break
            case 14: out = 4010; break
            case 41: out = 5002; break
            default: out = v; break
        }
        out
    }

    static Integer usingIf(Integer v) {
        Integer out

        if (v == 4)
            out = 4100
        else if (v == 10)
            out = 4012
        else if (v == 20)
            out = 5031
        else if (v == 2 || v == 3 || v == 15 || v == 16)
            out = 5030
        else if (v == 11 || v == 12 || v == 13)
            out = 4100
        else if (v == 14)
            out = 4010
        else if (v == 41)
            out = 5002
        else
            out = v
        out
    }

    @CompileStatic
    static Integer usingIfWithStatic(Integer v) {
        Integer out

        if (v == 4)
            out = 4100
        else if (v == 10)
            out = 4012
        else if (v == 20)
            out = 5031
        else if (v == 2 || v == 3 || v == 15 || v == 16)
            out = 5030
        else if (v == 11 || v == 12 || v == 13)
            out = 4100
        else if (v == 14)
            out = 4010
        else if (v == 41)
            out = 5002
        else
            out = v
        out
    }

    @Test
    void benchmark() {

        Integer resultUsingSwitch
        Integer resultUsingIf
        Integer resultUsingIfWithStatic

        benchmark {
            'Using Switch' {
                resultUsingSwitch = usingSwitch(500)
            }

            'Using If' {
                resultUsingIf = usingIf(500)
            }

            'Using If + CompileStatic' {
                resultUsingIfWithStatic = usingIfWithStatic(500)
            }
        }.prettyPrint()

        assert resultUsingSwitch == resultUsingIf
        assert resultUsingIf == resultUsingIfWithStatic
    }
}

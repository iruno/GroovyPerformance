import org.junit.Test

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class EpochToString {

    private final static String DateTimeFormatUTC = "yyyy-MM-dd HH:mm:ss 'UTC'"
    private final static DateTimeFormatter parserUTC = DateTimeFormatter.ofPattern(DateTimeFormatUTC)
    private final static String expirationTimeStr = '2012-02-17 03:00:00 UTC'
    Long exp = 1329447600

    @Test
    void benchmark() {
        String result1
        String result2

        benchmark {
            SimpleDateFormat {
                SimpleDateFormat format = new SimpleDateFormat(DateTimeFormatUTC)
                format.setTimeZone(TimeZone.getTimeZone("UTC"))
                Date datetime = new Date(exp * 1000)
                result1 = format.format(datetime)

            }
            DateTimeFormatter {
                LocalDateTime dateTime = LocalDateTime.ofEpochSecond(exp, 0, ZoneOffset.UTC)
                result2 = dateTime.format(parserUTC)

            }

        }.prettyPrint()

        println result1

        assert result1 == result2
        assert result1 == expirationTimeStr
    }

}

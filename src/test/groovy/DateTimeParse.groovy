import org.junit.Test

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class DateTimeParse {

    private final static String DateTimeFormatUTC = "yyyy-MM-dd HH:mm:ss 'UTC'"
    private final static DateTimeFormatter parserUTC = DateTimeFormatter.ofPattern(DateTimeFormatUTC)
    private final static String expirationTimeStr = '2012-02-17 03:00:00 UTC'

    @Test
    void benchmark() {
        Long result1
        Long result2

        benchmark {
            'SimpleDateFormat' {
                SimpleDateFormat format = new SimpleDateFormat(DateTimeFormatUTC)
                format.setTimeZone(TimeZone.getTimeZone("UTC"))
                Date dateTime = format.parse(expirationTimeStr)
                result1 = dateTime.time / 1000
            }
            'DateTimeFormatter' {
                LocalDateTime ldt = LocalDateTime.parse(expirationTimeStr, parserUTC)
                ZonedDateTime dateTime = ldt.atZone(ZoneOffset.UTC) //you might use a different zone
                result2 = dateTime.toEpochSecond()
            }
        }.prettyPrint()

        println result1

        assert result1 == result2
    }
}

import org.kethereum.crypto.test_data.getABIString
import kotlin.test.Test
import kotlin.test.assertNotNull

class TheTestData {

    @Test
    fun canGetTestData() {
        assertNotNull(getABIString("peepeth"))
    }

}

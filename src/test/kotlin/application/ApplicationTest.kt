package application

import org.junit.Assert
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.io.PrintStream

class ApplicationTest
{
    @Test
    fun `test a simple mytexttxt file with four words as an argument for application`() {
        val outputStream = ByteArrayOutputStream()
        val application = createApplication(outputStream)
        val filePath = ApplicationTest::class.java.getResource("/ApplicationInputFiles/mytext.txt").path

        application.run(filePath)

        val expectedOutput = "Number of words: 4${System.lineSeparator()}"
        val actualOutput = String(outputStream.toByteArray())
        Assert.assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun `test an empty file as an argument`() {
        val outputStream = ByteArrayOutputStream()
        val application = createApplication(outputStream)
        val filePath = ApplicationTest::class.java.getResource("/emptyFile.txt").path

        application.run(filePath)

        val expectedOutput = "Number of words: 0${System.lineSeparator()}"
        val actualOutput = String(outputStream.toByteArray())
        Assert.assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun `test no argument is provided`() {
        val outputStream = ByteArrayOutputStream()
        val application = createApplication(outputStream, "1Foo Ba3r.           Word")

        application.run()

        val expectedOutput = "Number of words: 1${System.lineSeparator()}"
        val actualOutput = String(outputStream.toByteArray())
        Assert.assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun `test no file exists, asks for manual input`() {
        val outputStream = ByteArrayOutputStream()
        val application = createApplication(outputStream, "word")

        application.run("nonexistingpath")

        val expectedOutput = "Number of words: 1${System.lineSeparator()}"
        val actualOutput = String(outputStream.toByteArray())
        Assert.assertEquals(expectedOutput, actualOutput)
    }

    private fun createApplication(outputStream: OutputStream, input: String = ""): Application {
        return Application(input.byteInputStream(), PrintStream(outputStream))
    }
}
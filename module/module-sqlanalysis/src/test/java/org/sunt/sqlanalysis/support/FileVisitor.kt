package org.sunt.sqlanalysis.support

import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.junit.jupiter.params.provider.Arguments
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.nio.charset.Charset
import java.util.stream.Stream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

object FileVisitor {

    fun visitZipFiles(zipFilePath: String, encode: Charset = Charset.defaultCharset(), vararg suffix: String): Stream<Array<String>> {
        suffix.sort()
        val zipFile = File(zipFilePath)
        val inputStream = ZipInputStream(FileInputStream(zipFile))
        var zipEntry: ZipEntry?
        val streamBuilder = Stream.builder<Array<String>>()
        while (inputStream.nextEntry.also { zipEntry = it } != null) {
            if (zipEntry!!.isDirectory) {
                continue
            }
            val fileName = zipEntry!!.name
            if (suffix.isNotEmpty()) {
                val fileSuffix = fileName.substringAfterLast('.')
                if (suffix.binarySearch(fileSuffix, String.CASE_INSENSITIVE_ORDER) <0) {
                    continue
                }
            }
            val bos = ByteArrayOutputStream()
            IOUtils.copy(inputStream, bos)
            val fileContent = String(bos.toByteArray(), encode)
            streamBuilder.add(arrayOf(fileName, fileContent))
            bos.close()
        }
        Arguments.arguments()
        return streamBuilder.build()
    }

    fun visitDirFiles(dirFilePath: String, encode: Charset = Charset.defaultCharset(), vararg suffix: String): Stream<Arguments> {
        val dirFile = File(dirFilePath)
        val files = FileUtils.listFiles(dirFile, suffix.takeIf { it.isNotEmpty() }, true )
        return files.stream().map { Arguments.arguments(it.path, FileUtils.readFileToString(it, encode)) }
    }

    fun visitFile(filePath: String, encode: Charset = Charset.defaultCharset()): String {
        return FileUtils.readFileToString(File(filePath), encode)
    }

}
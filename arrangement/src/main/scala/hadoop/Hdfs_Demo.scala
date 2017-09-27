package hadoop

import java.net.URI

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{Path, FileSystem}

/**
 * Created by YuiSol on 2017/9/26.
 */
object Hdfs_Demo {
  val uri :String = "hdfs://192.168.1.81:9000";
  def main(args: Array[String]) {
   delete("\\root")
    update("D:\\2b\\person.txt")
  }


  def delete(path: String) {
    val config: Configuration = new Configuration
    val fileSystem: FileSystem = FileSystem.get(new URI(uri), config, "root")
    fileSystem.delete(new Path(path), true)
  }

  def update(path: String) {
    val config: Configuration = new Configuration
    val fileSystem: FileSystem = FileSystem.get(new URI(uri), config, "root")
    fileSystem.copyFromLocalFile(false, new Path(path), new Path("/root/person.txt"))
    fileSystem.close
  }
}

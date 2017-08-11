package datageneration.phoneData.analysis.model.handledata;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileAlreadyExistsException;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


/**
 * Created by tourbis on 2017/8/1.
 */
public class FileTest extends FileOutputFormat<Text,Text> {
    public FileTest() {
        super();
    }

    @Override
    public void checkOutputSpecs(JobContext job) throws FileAlreadyExistsException, IOException {
        super.checkOutputSpecs(job);
    }

    @Override
    public Path getDefaultWorkFile(TaskAttemptContext context, String extension) throws IOException {
        return super.getDefaultWorkFile(context, extension);
    }

    @Override
    public synchronized OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException {
        return super.getOutputCommitter(context);
    }

    @Override
    public RecordWriter<Text, Text> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
        return null;
    }

    public static void setOutputPath(Job job, Path outputDir) {

    }

}

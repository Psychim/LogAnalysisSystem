package main.java.mapreduce.activitystatistic;

import main.java.mapreduce.writable.DateCityWritable;
import main.java.mapreduce.writable.DateWritable;
import main.java.mapreduce.writable.IntPairWritable;
import main.java.util.DateUtil;
import main.scala.Global;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;
import java.sql.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by root on 9/6/17.
 */
public class ActivityMapReducer {
    static private String JobName;
    static private String inputPath;
    static private String outputPath;
    static private int gmt;
    static private String dateFormatPattern;

    static {

        setJobName(Global.rawDataPath());
        setInputPath(System.getProperty("user.dir"));
        setOutputPath(Global.outputRoot() + Global.activityStatisticDir());
        setGmt(8);
        setDateFormatPattern("yyyy-MM-dd");
    }

    public static String getJobName() {
        return JobName;
    }

    public static void setJobName(String jobName) {
        JobName = jobName;
    }

    public static String getInputPath() {
        return inputPath;
    }

    public static void setInputPath(String inputPath) {
        ActivityMapReducer.inputPath = inputPath;
    }

    public static String getOutputPath() {
        return outputPath;
    }

    public static void setOutputPath(String outputPath) {
        ActivityMapReducer.outputPath = outputPath;
    }

    public static int getGmt() {
        return gmt;
    }

    public static void setGmt(int gmt) {
        ActivityMapReducer.gmt = gmt;
    }

    public static String getDateFormatPattern() {
        return dateFormatPattern;
    }

    public static void setDateFormatPattern(String dateFormatPattern) {
        ActivityMapReducer.dateFormatPattern = dateFormatPattern;
    }

    static public void run(Configuration conf)
            throws IOException, InterruptedException, ClassNotFoundException {
        FileSystem fs = FileSystem.get(conf);
        fs.delete(new Path(outputPath), true);
        fs.close();
        Job job = Job.getInstance(conf);
        job.setJarByClass(ActivityMapReducer.class);
        job.setJobName(JobName);
        job.setOutputKeyClass(DateCityWritable.class);
        job.setOutputValueClass(IntPairWritable.class);
        job.setMapOutputKeyClass(DateCityWritable.class);
        job.setMapOutputValueClass(Text.class);
        job.setMapperClass(ActivityMapper.class);
        job.setReducerClass(ActivityReducer.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath));
        job.waitForCompletion(true);
    }

    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.set("fs.default.name", "hdfs://scm001:9000");
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
        try {
            run(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ActivityMapper extends Mapper<LongWritable, Text, DateCityWritable, Text> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] parts = line.split("\t");
            Date d = new Date(Long.parseLong(parts[0]));
            Integer cid = Integer.parseInt(parts[2]);
            String phone = parts[1];
            //System.out.println(phone);
            context.write(new DateCityWritable(new DateWritable(DateUtil.transform(d, gmt), dateFormatPattern),
                    new IntWritable(cid)), new Text(phone));
        }
    }

    static class ActivityReducer extends Reducer<DateCityWritable, Text, DateCityWritable, IntPairWritable> {

        @Override
        protected void reduce(DateCityWritable key, Iterable<Text> values, Context context)
                throws IOException, InterruptedException {
            int pv = 0, uv = 0;
            Set<String> set = new TreeSet<String>();
            for (Text text : values) {
                String phone = text.toString();
                pv++;
                if (!set.contains(phone)) {
                    uv++;
                    set.add(phone);
                }
            }
            context.write(key, new IntPairWritable(new IntWritable(pv), new IntWritable(uv)));
        }
    }
}

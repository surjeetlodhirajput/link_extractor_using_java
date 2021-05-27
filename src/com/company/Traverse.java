package com.company;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.URL;
import java.util.*;
/*
Writer: Surjeet
contact: Surjeetrajput433@gmail.com
 */
class TraverseWholeWeb //extends Thread
{
    String link;
    String temp;
    String websiteLink;
    Set<String> remaining=new HashSet<>();
    Set<String> traversed=new HashSet<>();
    Set<String> tempSet=new HashSet<>();

    TraverseWholeWeb(String link,String w){
    this.link=link;
    this.websiteLink=new String(this.getDomainName(w));
    }

    //for getting the domain name of webite
    public  String getDomainName(String url) {
        try {


            if (!url.startsWith("http") && !url.startsWith("https")) {
                url = "http://" + url;
            }
            URL netUrl = new URL(url);
            String host = netUrl.getHost();
            if (host.startsWith("www")) {
                host = host.substring("www".length() + 1);
            }
            return host;
        }
        catch (IOException e)
        {
            System.out.print(e);
        }
return "";
    }
    public void run()
    {
        try {

            this.StoreAllLinkInRemainingSet(link);
            //boolean flag = true;
            while (!this.remaining.isEmpty()) {
                FileWriter fw = new FileWriter("Traversed"+link, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter op=new PrintWriter(bw);
                int i=0,h=0;
                this.traverseAllLinks();
                for (String s : remaining
                ) {
                    tempSet.add(s);
                    op.write(s+"\n");
                    System.out.println(s);//this line will onlly useful when you are using cn ide
                }
                remaining.clear();
                for (String e : traversed
                ) {
                    h++;
                    if (!tempSet.contains(e)) {
                     ++i;
                     remaining.add(e);
                    }

                }
/*loop:        for(String e:traversed){
        if(!remaining.contains(e)) {
            flag=true;
           break loop;
        }
        else
        {
            flag=false;
        }
};*/
                System.out.println("New Links Count: "+i+" before "+h);
                op.close();
                bw.close();
                fw.close();


            }


        }
        catch (IOException e)
        {
            System.out.println(e.getStackTrace());
        }
/*        this.store(); for saving the result in first opened file
  */  }
    public boolean traverseAllLinks()
    {

        for (String url:remaining
             ) {
            if(url.contains(websiteLink))
            {
                if(!tempSet.contains(url)) {
                    if (!url.contains(".zip") || !url.contains(".exe") || !url.contains(".pdf") || !url.contains(".xls") || !url.contains(".ppt") || !url.contains(".pptx") || !url.contains(".txt")|| !url.contains(".jpg") || !url.contains(".png")|| !url.contains(".mp4") || !url.contains(".mp3"))
                        this.page(url);
                    traversed.add(url);
                }

            }
            else{
                traversed.add(url);
                //return false;
            }

        }
        return true;
    }
//for storing traverse l=into the file
    public  void store()
    {
        try{
            FileWriter fileWriter=new FileWriter(link);
            for (String e:traversed)
            {
                fileWriter.write(e+"\n");
            }
            fileWriter.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getStackTrace());
        }
    }

    //for traversing single link
    public void   page(String link)
    {

        if(!(link.contains("https://")||link.contains("http://"))){
            link=new String("https://"+link);
        }

        try {
            Document d = Jsoup.connect(link).get();
            Elements links = d.select("a[href]");
            for (Element e:links
                 ) {
                traversed.add(e.attr("abs:href"));
            }

        }
        catch (Exception e){
            //you can write your won desired one
            System.out.println(e.getMessage());
        }

    }

    public   void StoreAllLinkInRemainingSet(String filename)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
        ){
            String line;
            while ((line=br.readLine())!=null)
            {
                this.remaining.add(line);
            }

        }
        catch (IOException e){
            System.out.print(e);
        }
        //after reading the frst file it will deleted
        new File(filename).delete();
    }

}


public class Traverse {
synchronized     public static boolean web(String link){
        boolean flag=true;
        String temp=new String(link);
        if(!(link.contains("https://")||link.contains("http://"))){
            link=new String("https://"+link);
        }
        link=new String(getName(link)+".txt");
        if( Traverse.page(temp)) {

            TraverseWholeWeb object = new TraverseWholeWeb(link, temp);
            //Thread thread=new Thread(object);
            //thread.start();
            object.run();
        }
        else {
            flag = false;
            System.out.println("outside if block");
        }return flag;
    }
 public static boolean page(String link)
{   boolean succ=true;

    if(!(link.contains("https://")||link.contains("http://"))){
        link=new String("https://"+link);
    }

    try {
    Document d = Jsoup.connect(link).get();
    Elements links = d.select("a[href]");
    succ=store(getName(link),links);

    }
catch (IOException e){
     succ=false;
                    }
    return succ;
}

//storing function
static boolean  store(String filename,Elements links){
    try {
        FileWriter file=new FileWriter(filename+".txt");
        for(Element e:links)
        file.write(e.attr("abs:href")+"\n");
        file.close();
    }
    catch (IOException e)
    {
return  false;
    }
    return true;
}
//get webite namw
    static  String getName(String url)
    {
        try {


            if (!url.startsWith("http") && !url.startsWith("https")) {
                url = "http://" + url;
            }
            URL netUrl = new URL(url);
            String host = netUrl.getHost();
            if (host.startsWith("www")) {
                host = host.substring("www".length() + 1);
            }

            return host.replaceAll(".com","");

        }
        catch (IOException e)
        {
            System.out.print(e);
        }
    return "";
    }
}

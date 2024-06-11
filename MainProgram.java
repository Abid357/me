import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainProgram
{
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException
    {
        List<String> locations = new ArrayList<>();

        PrintWriter pwDuration = new PrintWriter(new FileOutputStream("durations.csv"));
        PrintWriter pwDistance = new PrintWriter(new FileOutputStream("distances.csv"));

        String temp = "";
        String xmlStr = "";
        File file = null;

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        while ((temp = br.readLine()) != null)
        {
            locations.add(formatString(temp, " ", "+"));
        }

        for(String loc : locations)
        {
            pwDuration.print("," + formatString(loc, "\\+", " "));
            pwDistance.print("," + formatString(loc, "\\+", " "));
        }

        for(int i = 0; i < locations.size(); i++)
        {
            pwDuration.println();
            pwDuration.print(formatString(locations.get(i), "\\+", " "));

            pwDistance.println();
            pwDistance.print(formatString(locations.get(i), "\\+", " "));

            for(int j = 0; j < locations.size(); j++)
            {
                pwDuration.print(",");
                pwDistance.print(",");

                if(i != j)
                {
                    xmlStr = getXML(locations.get(i), locations.get(j));
                    file = getFileStreamFor(xmlStr);

                    //Route route = new Route(formatString(locations.get(i), "\\+", " "), formatString(locations.get(j), "\\+", " "), Route.convertDistance(getElementValue(file, "distance")), Route.convertDuration(getElementValue(file, "duration")));

                    pwDuration.print(Route.convertDuration(getElementValue(file, "duration")));
                    pwDistance.print(Route.convertDistance(getElementValue(file, "distance")));

                    System.out.println("Processed an element...");
                }
            }
        }
        pwDuration.close();
        pwDistance.close();

    }
    private static String getXML(String starting, String ending) throws IOException
    {
        String xmlStr = "";
        BufferedReader urlReader = new BufferedReader(new InputStreamReader(getURL(starting, ending).openStream()));
        xmlStr = urlReader.lines().collect(Collectors.joining("\n"));

        return xmlStr;
    }

    private static URL getURL(String starting, String ending) throws MalformedURLException
    {
        return new URL("https://maps.googleapis.com/maps/api/directions/xml?origin=" + starting + "&destination=" + ending + "&key=my_api_key");
    }

    private static String formatString(String location, String r, String w)
    {
        return location.replaceAll(r, w);
    }

    private static File getFileStreamFor(String text) throws FileNotFoundException
    {
        PrintWriter printWriter = new PrintWriter(new FileOutputStream("xml.txt"));
        printWriter.print(text);
        printWriter.close();

        return new File("xml.txt");
    }

    private static Double getElementValue(File file, String elementName) throws ParserConfigurationException, SAXException, IOException
    {
        String value = "-1";

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName(elementName);

        Node node = null;

        try
        {
            node = nList.item(nList.getLength() - 1);
        }
        catch (Exception e)
        {
            System.out.println(doc.getElementsByTagName("error_message").item(0).getTextContent());
            System.exit(-1);
        }

        if(node.getNodeType() == Node.ELEMENT_NODE)
        {
            Element element = (Element) node;
            value = element.getElementsByTagName("value").item(0).getTextContent();
        }

        return Double.parseDouble(value);
    }

}


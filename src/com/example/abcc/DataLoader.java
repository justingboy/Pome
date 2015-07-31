package com.example.abcc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DataLoader
{
  public List<String> getAuthors()
  {
    ArrayList <String>localArrayList = new ArrayList<String>();
    DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
    Object localObject = null;
    try
    {
      Document localDocument = localDocumentBuilderFactory.newDocumentBuilder().parse(getClass().getClassLoader().getResourceAsStream("com/kuaiyou/wl/poem.xml"));
      localObject = localDocument;
      NodeList localNodeList = ((Document) localObject).getElementsByTagName("node");
      int i = localNodeList.getLength();
      for (int j = 0; j < i; j++)
      {
        String str = ((Element)localNodeList.item(j)).getElementsByTagName("auth").item(0).getTextContent();
        if (!localArrayList.contains(str))
          localArrayList.add(str);
      }
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      while (true)
        localParserConfigurationException.printStackTrace();
    }
    catch (SAXException localSAXException)
    {
      while (true)
        localSAXException.printStackTrace();
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
    return localArrayList;
  }

  public List<Poem> getList()
  {
    ArrayList localArrayList = new ArrayList();
    DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
    Object localObject = null;
    try
    {
      Document localDocument = localDocumentBuilderFactory.newDocumentBuilder().parse(getClass().getClassLoader().getResourceAsStream("com/kuaiyou/wl/poem.xml"));
      localObject = localDocument;
      NodeList localNodeList = ((Document) localObject).getElementsByTagName("node");
      int i = localNodeList.getLength();
      for (int j = 0; j < i; j++)
      {
        Element localElement = (Element)localNodeList.item(j);
        localArrayList.add(new Poem(localElement.getElementsByTagName("title").item(0).getTextContent(), localElement.getElementsByTagName("auth").item(0).getTextContent(), localElement.getElementsByTagName("desc").item(0).getTextContent()));
      }
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      while (true)
        localParserConfigurationException.printStackTrace();
    }
    catch (SAXException localSAXException)
    {
      while (true)
        localSAXException.printStackTrace();
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
    return localArrayList;
  }

  public List<Poem> getList(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
    Object localObject = null;
    try
    {
      Document localDocument = localDocumentBuilderFactory.newDocumentBuilder().parse(getClass().getClassLoader().getResourceAsStream("com/kuaiyou/wl/poem.xml"));
      localObject = localDocument;
      NodeList localNodeList = ((Document) localObject).getElementsByTagName("node");
      int i = localNodeList.getLength();
      for (int j = 0; j < i; j++)
      {
        Element localElement = (Element)localNodeList.item(j);
        if (paramString.equals(localElement.getElementsByTagName("auth").item(0).getTextContent()))
          localArrayList.add(new Poem(localElement.getElementsByTagName("title").item(0).getTextContent(), paramString, localElement.getElementsByTagName("desc").item(0).getTextContent()));
      }
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      while (true)
        localParserConfigurationException.printStackTrace();
    }
    catch (SAXException localSAXException)
    {
      while (true)
        localSAXException.printStackTrace();
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
    return localArrayList;
  }

  public List<Poem> getListByTitle(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
    Object localObject = null;
    try
    {
      Document localDocument = localDocumentBuilderFactory.newDocumentBuilder().parse(getClass().getClassLoader().getResourceAsStream("org/vv/data/poem.xml"));
      localObject = localDocument;
      NodeList localNodeList = ((Document) localObject).getElementsByTagName("node");
      int i = localNodeList.getLength();
      for (int j = 0; j < i; j++)
      {
        Element localElement = (Element)localNodeList.item(j);
        String str = localElement.getElementsByTagName("title").item(0).getTextContent();
        if (str.indexOf(paramString) != -1)
          localArrayList.add(new Poem(str, localElement.getElementsByTagName("auth").item(0).getTextContent(), localElement.getElementsByTagName("desc").item(0).getTextContent()));
      }
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      while (true)
        localParserConfigurationException.printStackTrace();
    }
    catch (SAXException localSAXException)
    {
      while (true)
        localSAXException.printStackTrace();
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
    return localArrayList;
  }

  public List<String> getTitles()
  {
    ArrayList localArrayList = new ArrayList();
    DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
    Object localObject = null;
    try
    {
      Document localDocument = localDocumentBuilderFactory.newDocumentBuilder().parse(getClass().getClassLoader().getResourceAsStream("com/kuaiyou/wl/poem.xml"));
      localObject = localDocument;
      NodeList localNodeList = ((Document) localObject).getElementsByTagName("node");
      int i = localNodeList.getLength();
      for (int j = 0; ; j++)
      {
        if (j >= i)
          break ;
        String str = ((Element)localNodeList.item(j)).getElementsByTagName("title").item(0).getTextContent();
        if (str.indexOf("¡¤") == -1)
          break;
        str = str.substring(0, str.indexOf("¡¤"));
        if (!localArrayList.contains(str))
          localArrayList.add(str);
      }
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      while (true)
        localParserConfigurationException.printStackTrace();
    }
    catch (SAXException localSAXException)
    {
      while (true)
        localSAXException.printStackTrace();
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        String str;
        localIOException.printStackTrace();
        continue;
      }
    }
    label212: return localArrayList;
  }
}
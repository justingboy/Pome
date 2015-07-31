package com.example.abcc;

import java.io.Serializable;

public class Poem
  implements Serializable
{
  private static final long serialVersionUID = 5323975348253671460L;
  private String author;
  private String desc;
  private String title;

  public Poem(String paramString1, String paramString2, String paramString3)
  {
    this.title = paramString1;
    this.author = paramString2;
    this.desc = paramString3;
  }

  public String getAuthor()
  {
    return this.author;
  }

  public String getDesc()
  {
    return this.desc;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setAuthor(String paramString)
  {
    this.author = paramString;
  }

  public void setDesc(String paramString)
  {
    this.desc = paramString;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
}
package com.tapestry5inaction.services.impl;

import com.tapestry5inaction.entities.Article;
import com.tapestry5inaction.entities.Blog;
import com.tapestry5inaction.entities.Tag;
import com.tapestry5inaction.entities.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class

        DemoDataParser {

    private Logger logger;

    public DemoDataParser(Logger logger) {
        this.logger = logger;
    }

    public class DemoData{
        private Blog blog;
        private List<Article> articles;
        private List<User> users;
        private List<Tag> tags;

        public DemoData(Blog blog, List<Article> articles, List<User> users, List<Tag> tags) {
            this.blog = blog;
            this.articles = articles;
            this.users = users;
            this.tags = tags;
        }

        public Blog getBlog() {
            return blog;
        }

        public List<User> getUsers() {
            return users;
        }

        public List<Article> getArticles() {
            return articles;
        }

        public List<Tag> getTags() {
            return tags;
        }
    }

    public DemoData parse(URL resource) {

        logger.info(String.format("Parsing demo data %s", resource));

        long start = System.currentTimeMillis();

        Handler handler = new Handler();

        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();

            reader.setContentHandler(handler);
            reader.setEntityResolver(handler);

            InputSource source = new InputSource(resource.openStream());

            reader.parse(source);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        long elapsed = System.currentTimeMillis() - start;

        logger.info(String.format("Parsed demo data %d ms", elapsed));

        return new DemoData(handler.getBlog(), handler.getArticles(), handler.getUsers(), handler.getTags());
    }


    private class Handler extends DefaultHandler {

        private Blog blog;
        private Article article;

        private List<Article> articles  = new ArrayList<Article>();

        private List<User> users = new ArrayList<User>();

        private List<Tag> tags = new ArrayList<Tag>();
        
        private StringBuilder buffer;

        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {

            logger.info(localName);

            if (localName.equals("blog")) {
                blog = new Blog();
                blog.setName(attributes.getValue("name"));
                blog.setDescription(attributes.getValue("description"));
            } else if (localName.equals("article")) {
                article = new Article();
                article.setTitle(attributes.getValue("title"));
                article.setPublishDate(parseDate(attributes.getValue("publishDate")));
                article.setBlog(blog);
            }else if(localName.equals("user")){
                User user = new User();
                user.setName(attributes.getValue("name"));
                user.setPassword(DigestUtils.md5Hex(user.getName()));
                users.add(user);
            }else if(localName.equals("tag")){
                Tag tag = new Tag();
                tag.setName(attributes.getValue("name"));
                tags.add(tag);
            }

        }

        public void characters(char[] ch,
                               int start,
                               int length)
                throws SAXException {

            if (buffer == null)
                buffer = new StringBuilder(length);

            buffer.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {

            if (localName.equals("article")) {
                article.setContent(buffer.toString());
                articles.add(article);

                buffer = null;
            }
        }


        public Blog getBlog() {
            return blog;
        }

        public List<User> getUsers() {
            return users;
        }

        public List<Article> getArticles() {
            return articles;
        }

        public List<Tag> getTags() {
            return tags;
        }

        private Date parseDate(String date) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }


    }
}

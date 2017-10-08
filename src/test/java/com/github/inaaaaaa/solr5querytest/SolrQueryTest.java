package com.github.inaaaaaa.solr5querytest;

import org.apache.solr.util.AbstractSolrTestCase;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SolrQueryTest extends AbstractSolrTestCase {

    @BeforeClass
    public static void beforeClass() throws Exception {
        initCore("solrconfig.xml", "schema.xml");
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        assertU(delQ("*:*"));
        assertU(commit());
    }

    @Test
    public void test() throws Exception {
        // Add documents.
        assertU(adoc("user_id", "aaa",
                     "gender", "0",
                     "age", "0",
                     "comment", "hello"
                     ));
        assertU(adoc("user_id", "bbb",
                     "gender", "0",
                     "age", "0",
                     "comment", "hello"
                     ));
        assertU(adoc("user_id", "ccc",
                     "gender", "0",
                     "age", "0",
                     "comment", "hello"
                     ));
        assertU(commit());

        // Execute query.
        String res = JQ(req("qt", "/select",
                            "q", "*:*",
                            "wt", "json",
                            "rows", "0",
                            "distrib", "false",
                            "fq", "comment:hello"
                            ));

        // Assert here.
        System.out.println(res);
    }
}

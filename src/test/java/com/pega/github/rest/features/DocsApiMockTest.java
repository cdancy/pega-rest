/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pega.github.rest.features;

import com.pega.github.rest.PegaRestApi;
import com.pega.github.rest.PegaRestApiMetadata;
import com.pega.github.rest.domain.docs.Docs;
import com.pega.github.rest.internal.BasePegaRestMockTest;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Mock tests for the {@link DocsApi} class.
 */
@Test(groups = "unit", testName = "DocsApiMockTest")
public class DocsApiMockTest extends BasePegaRestMockTest {

    public void testGetDocs() throws Exception {
        MockWebServer server = mockEtcdJavaWebServer();

        server.enqueue(new MockResponse().setBody(payloadFromResource("/docs.json")).setResponseCode(200));
        PegaRestApi baseApi = api(server.getUrl("/"));
        DocsApi api = baseApi.docsApi();
        try {
            Docs docs = api.docs();
            assertNotNull(docs);
            assertTrue(docs.info().title().equals("Pega API"));
            assertSent(server, "GET", "/prweb/api/" + PegaRestApiMetadata.API_VERSION + "/docs");
        } finally {
            baseApi.close();
            server.shutdown();
        }
    }
}

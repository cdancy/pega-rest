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

import com.pega.github.rest.BasePegaRestApiLiveTest;
import com.pega.github.rest.domain.docs.Docs;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@Test(groups = "live", testName = "DocsApiLiveTest", singleThreaded = true)
public class DocsApiLiveTest extends BasePegaRestApiLiveTest {

    public void testGetDocs() {
        Docs docs = api().docs();
        assertNotNull(docs);
        assertTrue(docs.info().title().equals("Pega API"));
    }

    private DocsApi api() {
        return api.docsApi();
    }
}

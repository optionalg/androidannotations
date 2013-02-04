/**
 * Copyright (C) 2010-2012 eBusiness Information, Excilys Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.androidannotations.hierarchyviewer;

import java.io.IOException;

import org.androidannotations.AndroidAnnotationProcessor;
import org.androidannotations.utils.AAProcessorTestHelper;
import org.junit.Before;
import org.junit.Test;

public class HierarchyViewerActivityTest extends AAProcessorTestHelper {

	@Before
	public void setup() {
		addManifestProcessorParameter(HierarchyViewerActivityTest.class);
		addProcessor(AndroidAnnotationProcessor.class);
	}

	@Test
	public void activity_subclass_in_manifest_compiles() {
		addManifestProcessorParameter(HierarchyViewerActivityTest.class, "AndroidManifest.xml");
		CompileResult result = compileFiles(HierarchyViewerActivity.class);
		assertCompilationSuccessful(result);
	}

	@Test
	public void activity_in_manifest_does_not_compile() throws IOException {
		addManifestProcessorParameter(HierarchyViewerActivityTest.class, "NoDebbugableManifest.xml");
		CompileResult result = compileFiles(HierarchyViewerActivity.class);
		assertCompilationErrorOn(HierarchyViewerActivity.class, "@HierarchyViewerSupport", result);
	}

	@Test
	public void activity_not_in_manifest_compiles_with_warning() throws IOException {
		addManifestProcessorParameter(HierarchyViewerActivityTest.class, "NoInternetPermissionManifest.xml");
		CompileResult result = compileFiles(HierarchyViewerActivity.class);
		assertCompilationErrorOn(HierarchyViewerActivity.class, "@HierarchyViewerSupport", result);
	}
}
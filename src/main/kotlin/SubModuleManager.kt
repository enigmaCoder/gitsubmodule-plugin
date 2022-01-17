import org.gradle.api.Plugin
import org.gradle.api.Project

class SubModuleManager: Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create("submodules",SubModuleParams::class.java)
        project.tasks.create("printSomething") { task ->
            task.doLast {
                project.exec{ execSpec ->
                    execSpec.workingDir(extension.workingDirectory)
                    extension.submoduleList.forEach { urlPath ->
                        execSpec.commandLine("git","submodule","add", urlPath )
                    }
                }
            }
        }
    }

}
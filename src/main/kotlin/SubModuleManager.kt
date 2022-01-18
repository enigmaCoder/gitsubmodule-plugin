import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class SubModuleManager: Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create("submodules",SubModuleParams::class.java)
        project.tasks.create("printSomething") { task ->
            task.doLast {
                println("Welcome to gradle plugin : Satyam")
                project.exec{ execSpec ->
                    execSpec.workingDir(extension.workingDirectory)
                    extension.submoduleList.forEach { urlPath ->
                        execSpec.commandLine("git","submodule","add", urlPath )
                    }
                    File(extension.workingDirectory+"/settings.gradle").forEachLine {
                        println(it)
                    }
                }
            }
        }
    }

}
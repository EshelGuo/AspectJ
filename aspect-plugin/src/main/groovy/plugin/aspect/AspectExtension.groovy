package plugin.aspect

class AspectExtension {

    def aspectVersion

    def setAspectVersion(String aspectVersion){
        this.aspectVersion = aspectVersion
    }

    def getAspectVersion(){
        return this.aspectVersion
    }
}
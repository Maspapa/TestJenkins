def call(branchName) {
    
    echo "The value of BRANCH_NAME is: ${branchName}"
    def codeBranchName
    def anotherName
    switch (branchName) {
        case 'dev':
            codeBranchName = branchName
            anotherName='1'
            break
        case 'Prod':
            codeBranchName = branchName
            anotherName='2'
            break
        default:
            codeBranchName = "test"
            anotherName='3'
            break
    }
    return [CodeBranch:codeBranchName,AnotherBranch:anotherName]
}
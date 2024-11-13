def call(branchName) {
    
    echo "The value of BRANCH_NAME is: ${branchName}"
    def codeBranchName
    switch (branchName) {
        case 'dev':
            codeBranchName = branchName
            break
        case 'Prod':
            codeBranchName = branchName
            break
        default:
            codeBranchName = "test"
            break
    }
    return codeBranchName
}
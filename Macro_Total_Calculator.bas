Attribute VB_Name = "Module1"
Sub CalculateTotalCost()
 Call Application.OnKey("~", "EnterKeyPressed")
 Call Application.OnKey("{TAB}", "TabKeyPressed")
End Sub

Private Sub EnterKeyPressed()
    Dim selectedCell As Range
    Set selectedCell = Application.activeCell
    
    Dim stringVal As String
    stringVal = selectedCell.Value
    
    Dim indexOfUnderscore As Integer
    indexOfUnderscore = InStr(stringVal, "_")
    
    Dim total As Double
    total = -1
    
    If indexOfUnderscore <> 0 Then
        Dim lengthOfUsefulText As Integer
        lengthOfUsefulText = Len(stringVal) - indexOfUnderscore
        
        Dim usefulText As String
        usefulText = Right(stringVal, lengthOfUsefulText)
        
        Dim tokens() As String
        tokens() = Split(usefulText, "x")
        
        'if conversion gives error exit if-block
        On Error GoTo Done
        
        Dim quantity As Double
        quantity = CDbl(Trim(tokens(0)))
        
        Dim rate As Double
        rate = CDbl(Trim(tokens(1)))
        
        total = quantity * rate
    End If
    
Done:
    selectedCell.Offset(0, 1).Select
    Set selectedCell = Application.activeCell
    If total <> -1 Then
        selectedCell.Value = total
    End If
    selectedCell.Offset(1, -1).Select
End Sub


Private Sub TabKeyPressed()
    Dim selectedCell As Range
    Set selectedCell = Application.activeCell
    
    Dim stringVal As String
    stringVal = selectedCell.Value
    
    Dim indexOfUnderscore As Integer
    indexOfUnderscore = InStr(stringVal, "_")
    
    Dim total As Double
    total = -1
    
    If indexOfUnderscore <> 0 Then
        Dim lengthOfUsefulText As Integer
        lengthOfUsefulText = Len(stringVal) - indexOfUnderscore
        
        Dim usefulText As String
        usefulText = Right(stringVal, lengthOfUsefulText)
        
        Dim tokens() As String
        tokens() = Split(usefulText, "x")
        
        'if conversion gives error exit if-block
        On Error GoTo Done
        
        Dim quantity As Double
        quantity = CDbl(Trim(tokens(0)))
        
        Dim rate As Double
        rate = CDbl(Trim(tokens(1)))
        
        total = quantity * rate
    End If
    
Done:
    selectedCell.Offset(0, 1).Select
    Set selectedCell = Application.activeCell
    If total <> -1 Then
        selectedCell.Value = total
    End If
End Sub





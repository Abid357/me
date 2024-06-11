Private Sub CustomerComboBox_DropButtonClick()
    Static DropIsDown As Boolean

    Dim selectedCell As Range
    Set selectedCell = Application.ActiveCell
    
    If DropIsDown And selectedCell.Row > 2 And selectedCell.Column = 2 Then
        selectedCell.Value = CustomerComboBox.Value
    End If
    
    DropIsDown = Not (DropIsDown)
End Sub

Private Sub ItemComboBox_DropButtonClick()
    Static DropIsDown As Boolean

    Dim selectedCell As Range
    Set selectedCell = Application.ActiveCell
    
    If DropIsDown And selectedCell.Row > 2 And selectedCell.Column = 3 Then
        selectedCell.Value = ItemComboBox.Value
    End If
    
    DropIsDown = Not (DropIsDown)
End Sub

Private Sub PostButton_Click()
    Dim selectedCells As Range
    Set selectedCells = Selection
    
    Dim mTemp As String
    Dim mDate As Date
    Dim mCustomer As String
    Dim mItem As String
    Dim mQuantity As Double
    Dim mRate As Double
    Dim mDebit As Double
    Dim mCredit As Double
    Dim mRemark As String
    Dim mPosted As String
    
    Dim selectedRow As Range
    Dim sheetExists As Boolean
    Dim mSheet As Worksheet
    Dim mRow As Integer
    
    For Each currentRow In selectedCells.Rows
        Set selectedRow = Range(Cells(currentRow.Row, 1), Cells(currentRow.Row, 9))
    
        For Each cell In selectedRow
            If cell.Column = 1 Or cell.Column = 2 Then
                If IsEmpty(cell) = True Then
                    MsgBox "Please ensure that all mandatory fields have a value"
                    Exit Sub
                End If
            End If
        Next cell
        
        mPosted = Cells(currentRow.Row, 9).Value2
        If mPosted = "P" Then
            MsgBox "Row " & currentRow.Row & " has already been posted"
            Exit Sub
        End If
        
        mTemp = Cells(currentRow.Row, 1).Value2
        mDate = CDate(mTemp)
        mCustomer = Cells(currentRow.Row, 2).Value2
        mItem = Cells(currentRow.Row, 3).Value2
        mQuantity = Cells(currentRow.Row, 4).Value2
        mRate = Cells(currentRow.Row, 5).Value2
        mDebit = Cells(currentRow.Row, 6).Value2
        mCredit = Cells(currentRow.Row, 7).Value2
        mRemark = Cells(currentRow.Row, 8).Value2
        
        sheetExists = False
        For i = 1 To Worksheets.Count
            If Worksheets(i).Name = mCustomer Then
                sheetExists = True
            End If
        Next i

        If Not sheetExists Then
            MsgBox "Customer file for " & mCustomer & " does not exist"
            Exit Sub
        End If
        
        Set mSheet = Sheets(mCustomer)
        mRow = mSheet.Cells(Rows.Count, "A").End(xlUp).Row + 1
        
        mSheet.Cells(mRow, 1).Value2 = mDate
        mSheet.Cells(mRow, 2).Value2 = mItem
        If mQuantity = 0 Then
            mSheet.Cells(mRow, 6).Value2 = mCredit
        Else
            mSheet.Cells(mRow, 3).Value2 = mQuantity
            mSheet.Cells(mRow, 4).Value2 = mRate
            mSheet.Cells(mRow, 5).Value2 = mDebit
        End If
        mSheet.Cells(mRow, 8).Value2 = mRemark
        
        Cells(currentRow.Row, 9).Value2 = "P"
    Next currentRow
End Sub

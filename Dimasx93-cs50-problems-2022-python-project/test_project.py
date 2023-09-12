#04/05/2023 Start date
#CS50 Final Project CS50's Introduction to Programming with Python week 9
import pytest
import sys
from io import StringIO
from datetime import datetime
from unittest.mock import patch
from project import calculate_age,check_args,check_name

@pytest.fixture
def csv_file(tmpdir):
    csv_data = "names,date_of_birth\nJohn,01/01/1980\nJane,15/03/1992"
    csv_file = tmpdir.join("data.csv")
    csv_file.write(csv_data)
    return csv_file

@pytest.fixture
def mock_input(monkeypatch):
    user_input = StringIO()
    monkeypatch.setattr('sys.stdin', user_input)
    return user_input

def test_calculate_age():
    birth_date = datetime(2000, 1, 1)
    assert calculate_age(birth_date) == 23
    birth_date2 = datetime(1957, 6, 4)
    assert calculate_age(birth_date2) == 65

def test_check_args_valid_csv():
    sys.argv = ['script.py', 'data.csv']
    assert check_args() is None

def test_check_args_invalid_arguments():
    sys.argv = ['script.py']
    with pytest.raises(SystemExit):
        check_args()

    sys.argv = ['script.py', 'data.txt']
    with pytest.raises(SystemExit):
        check_args()

    sys.argv = ['script.py', 'data.csv', 'extra_arg']
    with pytest.raises(SystemExit):
        check_args()

def test_check_name_exists(csv_file, mock_input, capsys):
    mock_input.write('John\n')
    mock_input.seek(0)

    with patch('sys.argv', ['script.py', csv_file]):
        birth_dates = {'John': '01/01/1980'}
        check_name(birth_dates)

    captured = capsys.readouterr()
    assert "John is" in captured.out

def test_check_name_not_exists(csv_file, mock_input, capsys):
    mock_input.write('David\n')
    mock_input.seek(0)

    with patch('sys.argv', ['script.py', csv_file]):
        birth_dates = {'John': '01/01/1980'}
        check_name(birth_dates)

    captured = capsys.readouterr()
    assert "David was not found" in captured.out
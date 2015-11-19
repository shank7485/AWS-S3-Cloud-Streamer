import os
import tkMessageBox
import Tkinter 
from boto.s3.connection import S3Connection
from Tkinter import Label, Entry, Button
from tkFileDialog import askopenfilename


master = Tkinter.Tk()
Label(master, text='Name of the folder on Cloud').grid(row=0)
e1 = Entry(master)
e1.grid(row=0, column=1)

def upload():
    folder = e1.get()
    file_path = askopenfilename()
    
    AWS_ID = 'AKIAIZM62YVO4GGIHORQ'
    AWS_key = 'rYy5WmWfAeXzjaF1pBAW/ma2D75K+w14sdKve0Ut'
    
    conn = S3Connection(AWS_ID, AWS_key)

    bucket = conn.get_bucket('shank7485')
    
    path = ''.join((folder, '/'))
    
    key_name = os.path.split(file_path)[1]
    
    print key_name

    full_key_name = os.path.join(path, key_name)

    k = bucket.new_key(full_key_name)
    k.set_contents_from_filename(file_path)
    k.make_public()
    
    tkMessageBox.showinfo( "Information", "Upload complete.")

Button(master, text="Upload", command=upload).grid(row=2, column=0)

master.mainloop()

CREATE DATABASE sound_link

USE sound_link

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Product_Images]
(
[Product_Images_Id] [INT] NOT NULL,
[Name] NVARCHAR NOT NULL,
[Product_Details_Id] [INT] NOT NULL,
PRIMARY KEY CLUSTERED
(
[Product_Images_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Color]
(
[Color_Id] [INT] NOT NULL,
[Color] NVARCHAR NOT NULL,
[Status] NVARCHAR NOT NULL,
PRIMARY KEY CLUSTERED
(
[Color_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Product_Details]
(
[Product_Details_Id] [INT] NOT NULL ,
[Color] NVARCHAR NOT NULL,
[Product_id] [INT] NOT NULL,
[Quantity] [INT] NOT NULL,
[Activated] NVARCHAR NOT NULL,
[Product_price] [FLOAT] NOT NULL,
PRIMARY KEY CLUSTERED
(
[Product_Details_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Type_Product]
(
[Type_Product_Id] [INT] NOT NULL,
[Name] NVARCHAR,
[Description] NVARCHAR NOT NULL,
[Update_Day] [DATE] NOT NULL,
[Quantity] [INT] NOT NULL,
PRIMARY KEY CLUSTERED
(
[Type_Product_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Brands]
(
[Brand_Id] [INT] NOT NULL,
[Name] NVARCHAR NOT NULL,
[Date_Created] [DATE] NOT NULL,
[Description] NVARCHAR NOT NULL,
[Activated] NVARCHAR NOT NULL,
PRIMARY KEY CLUSTERED
(
[Brand_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Designs]
(
[Design_Id] [INT] NOT NULL,
[Name] NVARCHAR NOT NULL,
[Description] NVARCHAR NOT NULL,
[Activated] NVARCHAR NOT NULL,
PRIMARY KEY CLUSTERED
(
[Design_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Product]
(
[Product_Id] [INT] NOT NULL,
[Name] NVARCHAR NOT NULL,
[Description] NVARCHAR NOT NULL,
[Status] NVARCHAR NOT NULL,
[Thumnails] NVARCHAR NOT NULL,
[Total_Quantity] [INT] NOT NULL,
[Brand_Id] [INT] NOT NULL,
[Type_Product_Id] [INT] NOT NULL,
[Category_Id] [INT] NOT NULL,
[Design_Id] [INT] NOT NULL,
[Updated_Time] [DATE] NOT NULL,
[Updated_Person_Id] [INT] NOT NULL,
PRIMARY KEY CLUSTERED
(
[Product_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Category]
(
[Categories_Id] [INT] NOT NULL,
[Name] NVARCHAR NOT NULL,
[Describe] NVARCHAR NOT NULL,
[Updated_Time] [DATE] NOT NULL,
[Updated_Person_Id] [INT] NOT NULL,
PRIMARY KEY CLUSTERED
(
[Categories_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Warranty]
(
[Warranty_Id] [INT] NOT NULL,
[Status] NVARCHAR NOT NULL,
[Duration] [INT] NOT NULL,
[Start_Date] [DATE] NOT NULL,
[End_Date] [DATE] NOT NULL,
[Activated] NVARCHAR NOT NULL,
PRIMARY KEY CLUSTERED
(
[Warranty_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Product_Warranly]
(
[Product_Warranly_Id] [INT] NOT NULL,
[Product_Id] [INT] NOT NULL,
[Warranty_Id] [INT] NOT NULL,
PRIMARY KEY CLUSTERED
(
[Warranty_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Invoices_Details]
(
[Invoices_Details_Id] [INT] NOT NULL,
[Invoice_Id] [INT] NOT NULL,
[Product_Id] [INT] NOT NULL,
[Quantity] [INT] NOT NULL,
[Price] [FLOAT] NOT NULL,
PRIMARY KEY CLUSTERED
(
[Invoices_Details_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Code_Coupons]
(
[Code_Coupons_Id] [INT] NOT NULL,
[Code] [INT] NOT NULL,
[Activated] NVARCHAR NOT NULL,
[Coupons_Id] [INT] NOT NULL,
PRIMARY KEY CLUSTERED
(
[Code_Coupons_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Coupons]
(
[Coupons_Id] [INT] NOT NULL,
[Describe] NVARCHAR NOT NULL,
[Start_Day] [DATE] NOT NULL,
[End_Date] [DATE] NOT NULL,
[Quantity] [INT] NOT NULL,
[Promotional_Type] NVARCHAR NOT NULL,
[Status] NVARCHAR NOT NULL,
[Attribute] NVARCHAR NOT NULL,
PRIMARY KEY CLUSTERED
(
[Coupons_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Pays]
(
[Pay_Id] [INT] NOT NULL,
[Pay_Method_Id] [INT] NOT NULL,
[Clent_Id] [INT] NOT NULL,
[Invoice_Id] [INT] NOT NULL,
[Status] NVARCHAR NOT NULL,
[Money] [FLOAT] NOT NULL,
PRIMARY KEY CLUSTERED
(
[Pay_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Invoices]
(
[Invoice_Id] [INT] NOT NULL,
[Created_Time] [DATE] NOT NULL,
[Created_Person_Id] [INT] NOT NULL,
[Client_Id] [INT] NOT NULL,
[Origin_Price] [FLOAT] NOT NULL,
[Attribute_Discount] NVARCHAR NOT NULL,
[Note] NVARCHAR NOT NULL,
[Status_Invoice_Id] [INT] NOT NULL,
[Total_Quantity] [INT] NOT NULL,
[Discount_Price] [FLOAT] NOT NULL,
PRIMARY KEY CLUSTERED
(
[Invoice_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Users]
(
[User_Id] [INT] NOT NULL,
[Name] NVARCHAR NOT NULL,
[Birthday] [DATE] NOT NULL,
[Gender] NVARCHAR NOT NULL,
[Phone_number] NVARCHAR NOT NULL,
[Address] NVARCHAR NOT NULL,
[Email] NVARCHAR NOT NULL,
PRIMARY KEY CLUSTERED
(
[User_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Account]
(
[Account_Id] [INT] NOT NULL,
[Username] NVARCHAR NOT NULL,
[Password] NVARCHAR NOT NULL,
[User_Id] [INT] NOT NULL,
[Date_Created] [DATE] NOT NULL,
[Role_Id] [INT] NOT NULL,
[Update_Day] [DATE] NOT NULL,
[Activated] NVARCHAR NOT NULL,
PRIMARY KEY CLUSTERED
(
[Account_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Status_Invoice]
(
[Status_Invoice_Id] [INT] NOT NULL,
[Status] NVARCHAR NOT NULL,
PRIMARY KEY CLUSTERED
(
[Status_Invoice_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Client]
(
[Client_Id] [INT] NOT NULL,
[Name] NVARCHAR NOT NULL,
[Phone_Number] NVARCHAR NOT NULL,
PRIMARY KEY CLUSTERED
(
[Client_Id] ASC
)
WITH
(PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Pay_Method]
(
[Pay_Method_Id] [INT] NOT NULL,
[Method] NVARCHAR NOT NULL,
[Status] NVARCHAR NOT NULL,
[Content] NVARCHAR NOT NULL,
PRIMARY KEY CLUSTERED
(
[Pay_Method_Id] ASC
)
WITH
(PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Role]
(
[Role_Id] [INT] NOT NULL,
[Name] NVARCHAR NOT NULL,
[Status] NVARCHAR NOT NULL,
PRIMARY KEY CLUSTERED
(
[Role_Id] ASC
)
WITH
(PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Store]
(
[Store_Id] [INT] NOT NULL,
[Name] NVARCHAR NOT NULL,
[Description] NVARCHAR NOT NULL,
[Address] NVARCHAR NOT NULL,
PRIMARY KEY CLUSTERED
(
[Store_Id] ASC
)
WITH
(PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

ALTER TABLE Product_Images
ADD CONSTRAINT FK_Product_Images_Product_Details
FOREIGN KEY (Product_Details_Id) REFERENCES Product_Details(Product_Details_Id);

ALTER TABLE Product_Details
ADD CONSTRAINT FK_Product_Product_Details
FOREIGN KEY (Product_Id) REFERENCES Product(Product_Id);

ALTER TABLE Product
ADD CONSTRAINT FK_Type_Product_Product_Details
FOREIGN KEY (Type_Product_Id) REFERENCES Type_Product(Type_Product_Id);

ALTER TABLE Product
ADD CONSTRAINT FK_Product_Brands
FOREIGN KEY (Brand_Id) REFERENCES Brands(Brand_Id);

ALTER TABLE Product
ADD CONSTRAINT FK_Product_Designs
FOREIGN KEY (Design_Id) REFERENCES Designs(Design_Id);

ALTER TABLE Product_Warranly
ADD CONSTRAINT FK_Product_Warranly_Product
FOREIGN KEY (Product_Id) REFERENCES Product(Product_Id);

ALTER TABLE Product_Warranly
ADD CONSTRAINT FK_Product_Warranly_Warranly
FOREIGN KEY (Warranty_Id) REFERENCES Warranty(Warranty_Id);

ALTER TABLE Invoices_Details
ADD CONSTRAINT FK_Invoices_Details_Invoices
FOREIGN KEY (Invoice_Id) REFERENCES Invoices(Invoice_Id);

ALTER TABLE Invoices_Details
ADD CONSTRAINT FK_Invoices_Details_Product
FOREIGN KEY (Product_Id) REFERENCES Product(Product_Id);

ALTER TABLE Invoices
ADD CONSTRAINT FK_Invoices_Client
FOREIGN KEY (Client_Id) REFERENCES Client(Client_Id);

ALTER TABLE Invoices
ADD CONSTRAINT FK_Invoices_Status_Invoices
FOREIGN KEY (Status_Invoice_Id) REFERENCES Status_Invoice(Status_Invoice_Id);

ALTER TABLE Pays
ADD CONSTRAINT FK_Pays_Pay_Method
FOREIGN KEY (Pay_Method_Id) REFERENCES Pay_Method(Pay_Method_Id);

ALTER TABLE Pays
ADD CONSTRAINT FK_Pays_Client
FOREIGN KEY (Clent_Id) REFERENCES Client(Client_Id);

ALTER TABLE Pays
ADD CONSTRAINT FK_Pays_Invoices
FOREIGN KEY (Invoice_Id) REFERENCES Invoices(Invoice_Id);

ALTER TABLE Account
ADD CONSTRAINT FK_Account_Users
FOREIGN KEY (User_Id) REFERENCES Users(User_Id);

ALTER TABLE Account
ADD CONSTRAINT FK_Account_Role
FOREIGN KEY (Role_Id) REFERENCES Role(Role_Id);

INSERT INTO Product_Details
(Product_Details_Id, Color, Product_id, Quantity, Activated, Product_price)
VALUES
(1, 'Red', 1, 10, 'Yes', 100.0),
(2, 'Blue', 2, 20, 'Yes', 120.0),
(3, 'Green', 3, 15, 'Yes', 90.0),
(4, 'Black', 4, 30, 'No', 150.0),
(5, 'White', 5, 25, 'Yes', 110.0),
(6, 'Yellow', 6, 12, 'No', 130.0),
(7, 'Orange', 7, 18, 'Yes', 140.0),
(8, 'Pink', 8, 22, 'Yes', 95.0),
(9, 'Brown', 9, 28, 'No', 125.0),
(10, 'Purple', 10, 35, 'Yes', 160.0),
(11, 'Gray', 11, 17, 'Yes', 105.0),
(12, 'Cyan', 12, 14, 'No', 145.0),
(13, 'Magenta', 13, 21, 'Yes', 115.0),
(14, 'Lime', 14, 26, 'No', 135.0),
(15, 'Turquoise', 15, 19, 'Yes', 155.0);

INSERT INTO Color
(Color_Id, Color, Status)
VALUES
(1, 'Red', 'Active'),
(2, 'Blue', 'Active'),
(3, 'Green', 'Active'),
(4, 'Black', 'Active'),
(5, 'White', 'Active'),
(6, 'Yellow', 'Active'),
(7, 'Orange', 'Active'),
(8, 'Pink', 'Active'),
(9, 'Brown', 'Active'),
(10, 'Purple', 'Active'),
(11, 'Gray', 'Active'),
(12, 'Cyan', 'Active'),
(13, 'Magenta', 'Active'),
(14, 'Lime', 'Active'),
(15, 'Turquoise', 'Active');

INSERT INTO Product_Images
(Product_Images_Id, Name, Product_Details_Id)
VALUES
(1, 'Image 1', 1),
(2, 'Image 2', 2),
(3, 'Image 3', 3),
(4, 'Image 4', 4),
(5, 'Image 5', 5),
(6, 'Image 6', 6),
(7, 'Image 7', 7),
(8, 'Image 8', 8),
(9, 'Image 9', 9),
(10, 'Image 10', 10),
(11, 'Image 11', 11),
(12, 'Image 12', 12),
(13, 'Image 13', 13),
(14, 'Image 14', 14),
(15, 'Image 15', 15);

INSERT INTO Type_Product
(Type_Product_Id, Name, Description, Update_Day, Quantity)
VALUES
(1, 'Type 1', 'Description 1', '2023-11-02', 10),
(2, 'Type 2', 'Description 2', '2023-11-02', 15),
(3, 'Type 3', 'Description 3', '2023-11-02', 20),
(4, 'Type 4', 'Description 4', '2023-11-02', 25),
(5, 'Type 5', 'Description 5', '2023-11-02', 30),
(6, 'Type 6', 'Description 6', '2023-11-02', 35),
(7, 'Type 7', 'Description 7', '2023-11-02', 40),
(8, 'Type 8', 'Description 8', '2023-11-02', 45),
(9, 'Type 9', 'Description 9', '2023-11-02', 50),
(10, 'Type 10', 'Description 10', '2023-11-02', 55),
(11, 'Type 11', 'Description 11', '2023-11-02', 60),
(12, 'Type 12', 'Description 12', '2023-11-02', 65),
(13, 'Type 13', 'Description 13', '2023-11-02', 70),
(14, 'Type 14', 'Description 14', '2023-11-02', 75),
(15, 'Type 15', 'Description 15', '2023-11-02', 80);

INSERT INTO Brands
(Brand_Id, Name, Date_Created, Description, Activated)
VALUES
(1, 'Brand 1', '2023-11-02', 'Description 1', 'Yes'),
(2, 'Brand 2', '2023-11-02', 'Description 2', 'Yes'),
(3, 'Brand 3', '2023-11-02', 'Description 3', 'Yes'),
(4, 'Brand 4', '2023-11-02', 'Description 4', 'Yes'),
(5, 'Brand 5', '2023-11-02', 'Description 5', 'Yes'),
(6, 'Brand 6', '2023-11-02', 'Description 6', 'No'),
(7, 'Brand 7', '2023-11-02', 'Description 7', 'Yes'),
(8, 'Brand 8', '2023-11-02', 'Description 8', 'Yes'),
(9, 'Brand 9', '2023-11-02', 'Description 9', 'Yes'),
(10, 'Brand 10', '2023-11-02', 'Description 10', 'Yes'),
(11, 'Brand 11', '2023-11-02', 'Description 11', 'Yes'),
(12, 'Brand 12', '2023-11-02', 'Description 12', 'Yes'),
(13, 'Brand 13', '2023-11-02', 'Description 13', 'No'),
(14, 'Brand 14', '2023-11-02', 'Description 14', 'Yes'),
(15, 'Brand 15', '2023-11-02', 'Description 15', 'Yes');

INSERT INTO Designs
(Design_Id, Name, Description, Activated)
VALUES
(1, 'Design 1', 'Description 1', 'Yes'),
(2, 'Design 2', 'Description 2', 'Yes'),
(3, 'Design 3', 'Description 3', 'Yes'),
(4, 'Design 4', 'Description 4', 'Yes'),
(5, 'Design 5', 'Description 5', 'Yes'),
(6, 'Design 6', 'Description 6', 'No'),
(7, 'Design 7', 'Description 7', 'Yes'),
(8, 'Design 8', 'Description 8', 'Yes'),
(9, 'Design 9', 'Description 9', 'Yes'),
(10, 'Design 10', 'Description 10', 'Yes'),
(11, 'Design 11', 'Description 11', 'Yes'),
(12, 'Design 12', 'Description 12', 'Yes'),
(13, 'Design 13', 'Description 13', 'No'),
(14, 'Design 14', 'Description 14', 'Yes'),
(15, 'Design 15', 'Description 15', 'Yes');

INSERT INTO Product
(Product_Id, Name, Description, Status, Thumnails, Total_Quantity, Brand_Id, Type_Product_Id, Category_Id, Design_Id, Updated_Time, Updated_Person_Id)
VALUES
(1, 'Product 1', 'Description 1', 'Active', 'Thumb1.jpg', 100, 1, 1, 1, 1, '2023-11-02', 1),
(2, 'Product 2', 'Description 2', 'Active', 'Thumb2.jpg', 120, 2, 2, 2, 2, '2023-11-02', 2),
(3, 'Product 3', 'Description 3', 'Active', 'Thumb3.jpg', 90, 3, 3, 3, 3, '2023-11-02', 3),
(4, 'Product 4', 'Description 4', 'Inactive', 'Thumb4.jpg', 150, 4, 4, 4, 4, '2023-11-02', 4),
(5, 'Product 5', 'Description 5', 'Active', 'Thumb5.jpg', 110, 5, 5, 5, 5, '2023-11-02', 5),
(6, 'Product 6', 'Description 6', 'Active', 'Thumb6.jpg', 130, 6, 6, 6, 6, '2023-11-02', 6),
(7, 'Product 7', 'Description 7', 'Active', 'Thumb7.jpg', 140, 7, 7, 7, 7, '2023-11-02', 7),
(8, 'Product 8', 'Description 8', 'Active', 'Thumb8.jpg', 95, 8, 8, 8, 8, '2023-11-02', 8),
(9, 'Product 9', 'Description 9', 'Inactive', 'Thumb9.jpg', 125, 9, 9, 9, 9, '2023-11-02', 9),
(10, 'Product 10', 'Description 10', 'Active', 'Thumb10.jpg', 160, 10, 10, 10, 10, '2023-11-02', 10),
(11, 'Product 11', 'Description 11', 'Active', 'Thumb11.jpg', 105, 11, 11, 11, 11, '2023-11-02', 11),
(12, 'Product 12', 'Description 12', 'Inactive', 'Thumb12.jpg', 145, 12, 12, 12, 12, '2023-11-02', 12),
(13, 'Product 13', 'Description 13', 'Active', 'Thumb13.jpg', 115, 13, 13, 13, 13, '2023-11-02', 13),
(14, 'Product 14', 'Description 14', 'Active', 'Thumb14.jpg', 135, 14, 14, 14, 14, '2023-11-02', 14),
(15, 'Product 15', 'Description 15', 'Active', 'Thumb15.jpg', 155, 15, 15, 15, 15, '2023-11-02', 15);

INSERT INTO Category
(Categories_Id, Name, Describe, Updated_Time, Updated_Person_Id)
VALUES
(1, 'Category 1', 'Description 1', '2023-11-02', 1),
(2, 'Category 2', 'Description 2', '2023-11-02', 2),
(3, 'Category 3', 'Description 3', '2023-11-02', 3),
(4, 'Category 4', 'Description 4', '2023-11-02', 4),
(5, 'Category 5', 'Description 5', '2023-11-02', 5),
(6, 'Category 6', 'Description 6', '2023-11-02', 6),
(7, 'Category 7', 'Description 7', '2023-11-02', 7),
(8, 'Category 8', 'Description 8', '2023-11-02', 8),
(9, 'Category 9', 'Description 9', '2023-11-02', 9),
(10, 'Category 10', 'Description 10', '2023-11-02', 10),
(11, 'Category 11', 'Description 11', '2023-11-02', 11),
(12, 'Category 12', 'Description 12', '2023-11-02', 12),
(13, 'Category 13', 'Description 13', '2023-11-02', 13),
(14, 'Category 14', 'Description 14', '2023-11-02', 14),
(15, 'Category 15', 'Description 15', '2023-11-02', 15);

INSERT INTO Warranty
(Warranty_Id, Status, Duration, Start_Date, End_Date, Activated)
VALUES
(1, 'Active', 12, '2023-11-02', '2024-11-02', 'Yes'),
(2, 'Active', 24, '2023-11-02', '2025-11-02', 'Yes'),
(3, 'Active', 36, '2023-11-02', '2026-11-02', 'Yes'),
(4, 'Active', 6, '2023-11-02', '2024-05-02', 'Yes'),
(5, 'Active', 18, '2023-11-02', '2024-05-02', 'Yes'),
(6, 'Active', 12, '2023-11-02', '2024-11-02', 'No'),
(7, 'Active', 24, '2023-11-02', '2025-11-02', 'Yes'),
(8, 'Active', 36, '2023-11-02', '2026-11-02', 'Yes'),
(9, 'Active', 6, '2023-11-02', '2024-05-02', 'Yes'),
(10, 'Active', 18, '2023-11-02', '2024-05-02', 'Yes'),
(11, 'Active', 12, '2023-11-02', '2024-11-02', 'Yes'),
(12, 'Active', 24, '2023-11-02', '2025-11-02', 'Yes'),
(13, 'Active', 36, '2023-11-02', '2026-11-02', 'No'),
(14, 'Active', 6, '2023-11-02', '2024-05-02', 'Yes'),
(15, 'Active', 18, '2023-11-02', '2024-05-02', 'Yes');

INSERT INTO Product_Warranly
(Product_Warranly_Id, Product_Id, Warranty_Id)
VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4),
(5, 5, 5),
(6, 6, 6),
(7, 7, 7),
(8, 8, 8),
(9, 9, 9),
(10, 10, 10),
(11, 11, 11),
(12, 12, 12),
(13, 13, 13),
(14, 14, 14),
(15, 15, 15);

INSERT INTO Invoices_Details
(Invoices_Details_Id, Invoice_Id, Product_Id, Quantity, Price)
VALUES
(1, 1, 1, 2, 50.00),
(2, 2, 2, 3, 75.00),
(3, 3, 3, 1, 30.00),
(4, 4, 4, 4, 100.00),
(5, 5, 5, 2, 50.00),
(6, 6, 6, 3, 75.00),
(7, 7, 7, 1, 30.00),
(8, 8, 8, 4, 100.00),
(9, 9, 9, 2, 50.00),
(10, 10, 10, 3, 75.00),
(11, 11, 11, 1, 30.00),
(12, 12, 12, 4, 100.00),
(13, 13, 13, 2, 50.00),
(14, 14, 14, 3, 75.00),
(15, 15, 15, 1, 30.00);

INSERT INTO Code_Coupons
(Code_Coupons_Id, Code, Activated, Coupons_Id)
VALUES
(1, 12345, 'Yes', 1),
(2, 67890, 'Yes', 2),
(3, 54321, 'No', 3),
(4, 98765, 'Yes', 4),
(5, 11111, 'No', 5),
(6, 22222, 'Yes', 6),
(7, 33333, 'Yes', 7),
(8, 44444, 'No', 8),
(9, 55555, 'Yes', 9),
(10, 66666, 'Yes', 10),
(11, 77777, 'No', 11),
(12, 88888, 'Yes', 12),
(13, 99999, 'Yes', 13),
(14, 00000, 'No', 14),
(15, 12121, 'Yes', 15);

INSERT INTO Coupons
(Coupons_Id, Describe, Start_Day, End_Date, Quantity, Promotional_Type, Status, Attribute)
VALUES
(1, 'Coupon 1', '2023-11-02', '2023-12-02', 100, 'Discount', 'Active', 'Type A'),
(2, 'Coupon 2', '2023-11-02', '2023-12-02', 50, 'Free Shipping', 'Active', 'Type B'),
(3, 'Coupon 3', '2023-11-02', '2023-12-02', 200, 'Discount', 'Active', 'Type A'),
(4, 'Coupon 4', '2023-11-02', '2023-12-02', 75, 'Discount', 'Active', 'Type C'),
(5, 'Coupon 5', '2023-11-02', '2023-12-02', 150, 'Free Shipping', 'Active', 'Type B'),
(6, 'Coupon 6', '2023-11-02', '2023-12-02', 300, 'Discount', 'Active', 'Type A'),
(7, 'Coupon 7', '2023-11-02', '2023-12-02', 50, 'Discount', 'Active', 'Type C'),
(8, 'Coupon 8', '2023-11-02', '2023-12-02', 200, 'Free Shipping', 'Active', 'Type B'),
(9, 'Coupon 9', '2023-11-02', '2023-12-02', 100, 'Discount', 'Active', 'Type A'),
(10, 'Coupon 10', '2023-11-02', '2023-12-02', 250, 'Free Shipping', 'Active', 'Type B'),
(11, 'Coupon 11', '2023-11-02', '2023-12-02', 50, 'Discount', 'Active', 'Type C'),
(12, 'Coupon 12', '2023-11-02', '2023-12-02', 150, 'Discount', 'Active', 'Type A'),
(13, 'Coupon 13', '2023-11-02', '2023-12-02', 300, 'Free Shipping', 'Active', 'Type B'),
(14, 'Coupon 14', '2023-11-02', '2023-12-02', 75, 'Discount', 'Active', 'Type A'),
(15, 'Coupon 15', '2023-11-02', '2023-12-02', 200, 'Discount', 'Active', 'Type C');

INSERT INTO Pays
(Pay_Id, Pay_Method_Id, Clent_Id, Invoice_Id, Status, Money)
VALUES
(1, 1, 1, 1, 'Paid', 50.00),
(2, 2, 2, 2, 'Paid', 75.00),
(3, 3, 3, 3, 'Paid', 30.00),
(4, 4, 4, 4, 'Paid', 100.00),
(5, 5, 5, 5, 'Paid', 50.00),
(6, 6, 6, 6, 'Paid', 75.00),
(7, 7, 7, 7, 'Paid', 30.00),
(8, 8, 8, 8, 'Paid', 100.00),
(9, 9, 9, 9, 'Paid', 50.00),
(10, 10, 10, 10, 'Paid', 75.00),
(11, 11, 11, 11, 'Paid', 30.00),
(12, 12, 12, 12, 'Paid', 100.00),
(13, 13, 13, 13, 'Paid', 50.00),
(14, 14, 14, 14, 'Paid', 75.00),
(15, 15, 15, 15, 'Paid', 30.00);

INSERT INTO Invoices
(Invoice_Id, Created_Time, Created_Person_Id, Client_Id, Origin_Price, Attribute_Discount, Note, Status_Invoice_Id, Total_Quantity, Discount_Price)
VALUES
(1, '2023-11-02', 1, 1, 150.00, 'Discount Type A', 'Order 1', 1, 2, 20.00),
(2, '2023-11-03', 2, 2, 200.00, 'Discount Type B', 'Order 2', 2, 3, 30.00),
(3, '2023-11-04', 3, 3, 100.00, 'Discount Type A', 'Order 3', 3, 1, 10.00),
(4, '2023-11-05', 4, 4, 250.00, 'Discount Type C', 'Order 4', 4, 4, 40.00),
(5, '2023-11-06', 5, 5, 150.00, 'Discount Type B', 'Order 5', 5, 2, 20.00),
(6, '2023-11-07', 6, 6, 200.00, 'Discount Type A', 'Order 6', 6, 3, 30.00),
(7, '2023-11-08', 7, 7, 100.00, 'Discount Type C', 'Order 7', 7, 1, 10.00),
(8, '2023-11-09', 8, 8, 250.00, 'Discount Type B', 'Order 8', 8, 4, 40.00),
(9, '2023-11-10', 9, 9, 150.00, 'Discount Type A', 'Order 9', 9, 2, 20.00),
(10, '2023-11-11', 10, 10, 200.00, 'Discount Type B', 'Order 10', 10, 3, 30.00),
(11, '2023-11-12', 11, 11, 100.00, 'Discount Type C', 'Order 11', 11, 1, 10.00),
(12, '2023-11-13', 12, 12, 250.00, 'Discount Type A', 'Order 12', 12, 4, 40.00),
(13, '2023-11-14', 13, 13, 150.00, 'Discount Type B', 'Order 13', 13, 2, 20.00),
(14, '2023-11-15', 14, 14, 200.00, 'Discount Type A', 'Order 14', 14, 3, 30.00),
(15, '2023-11-16', 15, 15, 100.00, 'Discount Type C', 'Order 15', 15, 1, 10.00);

INSERT INTO Users
(User_Id, Name, Birthday, Gender, Phone_number, Address, Email)
VALUES
(1, 'User 1', '1990-01-01', 'Male', '1234567890', '123 Street, City', 'user1@example.com'),
(2, 'User 2', '1991-02-02', 'Female', '2345678901', '456 Street, City', 'user2@example.com'),
(3, 'User 3', '1992-03-03', 'Male', '3456789012', '789 Street, City', 'user3@example.com'),
(4, 'User 4', '1993-04-04', 'Female', '4567890123', '012 Street, City', 'user4@example.com'),
(5, 'User 5', '1994-05-05', 'Male', '5678901234', '345 Street, City', 'user5@example.com'),
(6, 'User 6', '1995-06-06', 'Female', '6789012345', '678 Street, City', 'user6@example.com'),
(7, 'User 7', '1996-07-07', 'Male', '7890123456', '901 Street, City', 'user7@example.com'),
(8, 'User 8', '1997-08-08', 'Female', '8901234567', '234 Street, City', 'user8@example.com'),
(9, 'User 9', '1998-09-09', 'Male', '9012345678', '567 Street, City', 'user9@example.com'),
(10, 'User 10', '1999-10-10', 'Female', '0123456789', '890 Street, City', 'user10@example.com'),
(11, 'User 11', '2000-11-11', 'Male', '1234567890', '123 Street, City', 'user11@example.com'),
(12, 'User 12', '2001-12-12', 'Female', '2345678901', '456 Street, City', 'user12@example.com'),
(13, 'User 13', '2002-01-13', 'Male', '3456789012', '789 Street, City', 'user13@example.com'),
(14, 'User 14', '2003-02-14', 'Female', '4567890123', '012 Street, City', 'user14@example.com'),
(15, 'User 15', '2004-03-15', 'Male', '5678901234', '345 Street, City', 'user15@example.com');

INSERT INTO Account
(Account_Id, Username, Password, User_Id, Date_Created, Role_Id, Update_Day, Activated)
VALUES
(1, 'user1', 'password1', 1, '2023-11-02', 1, '2023-11-02', 'Active'),
(2, 'user2', 'password2', 2, '2023-11-03', 2, '2023-11-03', 'Active'),
(3, 'user3', 'password3', 3, '2023-11-04', 3, '2023-11-04', 'Active'),
(4, 'user4', 'password4', 4, '2023-11-05', 4, '2023-11-05', 'Active'),
(5, 'user5', 'password5', 5, '2023-11-06', 5, '2023-11-06', 'Active'),
(6, 'user6', 'password6', 6, '2023-11-07', 6, '2023-11-07', 'Active'),
(7, 'user7', 'password7', 7, '2023-11-08', 7, '2023-11-08', 'Active'),
(8, 'user8', 'password8', 8, '2023-11-09', 8, '2023-11-09', 'Active'),
(9, 'user9', 'password9', 9, '2023-11-10', 9, '2023-11-10', 'Active'),
(10, 'user10', 'password10', 10, '2023-11-11', 10, '2023-11-11', 'Active'),
(11, 'user11', 'password11', 11, '2023-11-12', 11, '2023-11-12', 'Active'),
(12, 'user12', 'password12', 12, '2023-11-13', 12, '2023-11-13', 'Active'),
(13, 'user13', 'password13', 13, '2023-11-14', 13, '2023-11-14', 'Active'),
(14, 'user14', 'password14', 14, '2023-11-15', 14, '2023-11-15', 'Active'),
(15, 'user15', 'password15', 15, '2023-11-16', 15, '2023-11-16', 'Active');

INSERT INTO Status_Invoice
(Status_Invoice_Id, Status)
VALUES
(1, 'Pending'),
(2, 'Paid'),
(3, 'Shipped'),
(4, 'Delivered'),
(5, 'Cancelled'),
(6, 'Refunded'),
(7, 'On Hold'),
(8, 'Processing'),
(9, 'In Progress'),
(10, 'Completed'),
(11, 'Confirmed'),
(12, 'Awaiting Payment'),
(13, 'Reviewing'),
(14, 'Approved'),
(15, 'Rejected');

INSERT INTO Client
(Client_Id, Name, Phone_Number)
VALUES
(1, 'Client 1', '1234567890'),
(2, 'Client 2', '2345678901'),
(3, 'Client 3', '3456789012'),
(4, 'Client 4', '4567890123'),
(5, 'Client 5', '5678901234'),
(6, 'Client 6', '6789012345'),
(7, 'Client 7', '7890123456'),
(8, 'Client 8', '8901234567'),
(9, 'Client 9', '9012345678'),
(10, 'Client 10', '0123456789'),
(11, 'Client 11', '1234567890'),
(12, 'Client 12', '2345678901'),
(13, 'Client 13', '3456789012'),
(14, 'Client 14', '4567890123'),
(15, 'Client 15', '5678901234');

INSERT INTO Pay_Method
(Pay_Method_Id, Method, Status, Content)
VALUES
(1, 'Credit Card', 'Active', 'Accepts credit card payments.'),
(2, 'Cash On Delivery', 'Active', 'Payment is made in cash upon delivery.'),
(3, 'Bank Transfer', 'Active', 'Payment is made via bank transfer.'),
(4, 'PayPal', 'Active', 'Accepts PayPal payments.'),
(5, 'Online Payment Gateway', 'Active', 'Payment is made through an online payment gateway.'),
(6, 'Cryptocurrency', 'Active', 'Accepts cryptocurrency payments.'),
(7, 'Mobile Wallet', 'Active', 'Payment is made through a mobile wallet.'),
(8, 'Check', 'Active', 'Accepts check payments.'),
(9, 'Payment Plan', 'Active', 'Offers payment plans for customers.'),
(10, 'Other', 'Active', 'Other payment methods.'),
(11, 'Bank Draft', 'Active', 'Accepts bank draft payments.'),
(12, 'Money Order', 'Active', 'Accepts money order payments.'),
(13, 'Online Bank Transfer', 'Active', 'Payment is made through online bank transfer.'),
(14, 'Google Wallet', 'Active', 'Accepts Google Wallet payments.'),
(15, 'Apple Pay', 'Active', 'Accepts Apple Pay payments.');

INSERT INTO Role
(Role_Id, Name, Status)
VALUES
(1, 'Admin', 'Active'),
(2, 'Manager', 'Active'),
(3, 'Salesperson', 'Active'),
(4, 'Customer Service', 'Active'),
(5, 'Accountant', 'Active'),
(6, 'Marketing', 'Active'),
(7, 'IT Support', 'Active'),
(8, 'Warehouse Staff', 'Active'),
(9, 'Quality Control', 'Active'),
(10, 'Human Resources', 'Active'),
(11, 'Supervisor', 'Active'),
(12, 'Team Lead', 'Active'),
(13, 'Project Manager', 'Active'),
(14, 'Developer', 'Active'),
(15, 'Designer', 'Active');

INSERT INTO Store
(Store_Id, Name, Description, Address)
VALUES
(1, 'Store 1', 'Description of Store 1', 'Address of Store 1'),
(2, 'Store 2', 'Description of Store 2', 'Address of Store 2'),
(3, 'Store 3', 'Description of Store 3', 'Address of Store 3'),
(4, 'Store 4', 'Description of Store 4', 'Address of Store 4'),
(5, 'Store 5', 'Description of Store 5', 'Address of Store 5'),
(6, 'Store 6', 'Description of Store 6', 'Address of Store 6'),
(7, 'Store 7', 'Description of Store 7', 'Address of Store 7'),
(8, 'Store 8', 'Description of Store 8', 'Address of Store 8'),
(9, 'Store 9', 'Description of Store 9', 'Address of Store 9'),
(10, 'Store 10', 'Description of Store 10', 'Address of Store 10'),
(11, 'Store 11', 'Description of Store 11', 'Address of Store 11'),
(12, 'Store 12', 'Description of Store 12', 'Address of Store 12'),
(13, 'Store 13', 'Description of Store 13', 'Address of Store 13'),
(14, 'Store 14', 'Description of Store 14', 'Address of Store 14'),
(15, 'Store 15', 'Description of Store 15', 'Address of Store 15');